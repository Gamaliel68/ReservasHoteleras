-- Sistema de reservas hoteleras
-- tablas empleado, huesped, habitacion, reserva, ticket

-- en una proxima version se agregara el sistema de pagos y facturacion

--===================================================
-- (solo desarrollo) para borrar todo y volver a ejecutar el script, descomentar:
-- DROP TABLE IF EXISTS ticket, reserva, habitacion, huesped, empleado CASCADE;
-- DROP SEQUENCE IF EXISTS ticket_numero_seq;
--===================================================

-- necesaria para la restriccion anti-solapamiento de reservas (EXCLUDE USING gist)
CREATE EXTENSION IF NOT EXISTS btree_gist;

-- Tabla empleado
-- los roles de los empleados aun no definen los permisos que tiene cada empleado
CREATE TABLE empleado(
                         id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                         nombre VARCHAR(80) NOT NULL,
                         apellido VARCHAR(80) NOT NULL,
                         username VARCHAR(50) NOT NULL UNIQUE,
    -- aqui se guarda el hash de la contrasena (BCrypt) y evitamos guadarla como texto plano
                         password_hash VARCHAR(255) NOT NULL,
    -- con el VARCHAR + CHECK es mas facil de modificar nuevos roles
                         rol VARCHAR(80) NOT NULL
                             CHECK ( rol IN ('ADMIN', 'GERENTE', 'RECEPCIONISTA')),
                         activo BOOLEAN NOT NULL DEFAULT TRUE,
                         fecha_baja TIMESTAMPTZ,
                         motivo_baja TEXT,
    -- esto es para saber quien dio de baja el empleado
                         baja_por_id BIGINT REFERENCES empleado(id),
    -- fecha de registro del empleado, se llena sola por el default now
                         creado_en TIMESTAMPTZ NOT NULL DEFAULT now(),
    -- un empleado inactivo siempre debe tener fecha de baja
                         CHECK ( activo OR fecha_baja IS NOT NULL )
);


-- Tabla huesped
-- los clientes no inician sesion ni guardan datos, lo hacen los empleados desde la plataforma
CREATE TABLE huesped (
                         id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                         nombre VARCHAR(80) NOT NULL,
                         apellido VARCHAR(80) NOT NULL,
                         tipo_documento VARCHAR(40) NOT NULL, -- INE, PASAPORTE ETC
                         numero_documento VARCHAR(40) NOT NULL,
    -- el email es opcional (UNIQUE permite varios NULL)
                         email VARCHAR(150) UNIQUE,
                         telefono VARCHAR(30),
                         nacionalidad VARCHAR(80),
                         creado_en TIMESTAMPTZ NOT NULL DEFAULT now(),
                         UNIQUE (tipo_documento, numero_documento)
);

-- Tabla habitacion
CREATE TABLE habitacion(
                           id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                           numero SMALLINT NOT NULL UNIQUE
                               CHECK ( numero > 0 ),
                           piso SMALLINT NOT NULL,
                           tipo VARCHAR(50) NOT NULL
                               CHECK ( tipo IN ('SENCILLA', 'DOBLE', 'TRIPLE', 'FAMILIAR', 'SUITE')),
                           capacidad SMALLINT NOT NULL
                               CHECK ( capacidad > 0 ),
                           precio_noche NUMERIC(10,2) NOT NULL
                               CHECK ( precio_noche > 0 ),
                           estado VARCHAR(50) NOT NULL DEFAULT 'DISPONIBLE'
                               CHECK ( estado IN ('DISPONIBLE', 'OCUPADA', 'LIMPIEZA', 'MANTENIMIENTO')),
                           descripcion TEXT,
                           activa BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla reserva
-- esta tabla relaciona huesped con habitacion
CREATE TABLE reserva(
                        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                        codigo VARCHAR(20) NOT NULL UNIQUE,
                        huesped_id BIGINT NOT NULL REFERENCES huesped(id),
                        habitacion_id BIGINT NOT NULL REFERENCES habitacion(id),
                        empleado_id BIGINT NOT NULL REFERENCES empleado(id), -- empleado que registro la reservacion
                        fecha_entrada DATE NOT NULL, -- para cambios.
                        fecha_salida DATE NOT NULL,
                        num_huespedes SMALLINT NOT NULL
                            CHECK ( num_huespedes > 0 ),
                        estado VARCHAR(50) NOT NULL DEFAULT 'CONFIRMADA'
                            CHECK ( estado IN ('CONFIRMADA', 'CHECK_IN', 'CHECK_OUT', 'CANCELADA') ),
                        precio_total NUMERIC(10,2) NOT NULL
                            CHECK ( precio_total > 0 ),
                        observaciones TEXT,
                        creado_en TIMESTAMPTZ NOT NULL DEFAULT now(),
                        actualizado_en TIMESTAMPTZ NOT NULL DEFAULT now(),
                        CHECK ( fecha_salida > fecha_entrada ),

    -- impedimos dos reservas activas en la misma habitacion en fechas que se cruzan
    -- el dia de salida queda libre para otra entrada
                        CONSTRAINT reserva_sin_solapamiento EXCLUDE USING gist (
                            habitacion_id WITH =,
                            daterange(fecha_entrada, fecha_salida, '[)') WITH &&
                            ) WHERE ( estado IN ('CONFIRMADA', 'CHECK_IN') )
);

CREATE INDEX idx_reserva_huesped ON reserva(huesped_id);
CREATE INDEX idx_reserva_habitacion ON reserva(habitacion_id);
CREATE INDEX idx_reserva_fechas ON reserva(fecha_entrada, fecha_salida);
CREATE INDEX idx_reserva_estado ON reserva(estado);

-- Tabla ticket
-- comprobante de cobro en establecimiento
-- una reserva puede tener varios tickets (anticipo + saldo)
CREATE SEQUENCE ticket_numero_seq;

CREATE TABLE ticket (
                        id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    -- numero consecutivo que se imprime, se genera solo (T-000001, T-000002...)
                        numero VARCHAR(20) NOT NULL UNIQUE
                                                        DEFAULT ('T-' || lpad(nextval('ticket_numero_seq')::TEXT, 6, '0')),
                        reserva_id BIGINT NOT NULL REFERENCES reserva(id),
                        empleado_id BIGINT NOT NULL REFERENCES empleado(id), -- empleado que cobro
                        monto NUMERIC(10,2) NOT NULL
                            CHECK ( monto > 0 ),
                        metodo_pago VARCHAR(50) NOT NULL
                            CHECK ( metodo_pago IN ('EFECTIVO', 'TARJETA', 'TRANSFERENCIA')),
                        estado VARCHAR(20) NOT NULL DEFAULT 'EMITIDO'
                            CHECK ( estado IN ('EMITIDO', 'ANULADO')),
                        emitido_en TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_ticket_reserva ON ticket(reserva_id);