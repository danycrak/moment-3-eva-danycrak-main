CREATE TABLE IF NOT EXISTS film (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100),
    director VARCHAR(100),
    duration DECIMAL(10, 2),
    genre VARCHAR(255),
    description TEXT,
    expence DECIMAL(10, 2) --gastos de la pelicula
    );

CREATE TABLE IF NOT EXISTS scene (
    id SERIAL PRIMARY KEY,
    description TEXT,
    budget DECIMAL(10, 2), -- Presupuesto de escena
    minutes DECIMAL(10, 2),
    location VARCHAR(255),
    effects VARCHAR(255),
    film_id INT NOT NULL,-- Clave foránea referenciando a la tabla Film
    FOREIGN KEY (film_id) REFERENCES film(id)
    );

CREATE TABLE IF NOT EXISTS characters (
    id SERIAL PRIMARY KEY,
    description TEXT,
    cost DECIMAL(10, 2),
    actor VARCHAR(255),
    roles VARCHAR(50),
    scene_id INT NOT NULL,
    FOREIGN KEY (scene_id) REFERENCES scene(id)
    );

