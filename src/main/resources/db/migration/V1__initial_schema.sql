CREATE TABLE users (
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(150)        NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    created_at TIMESTAMP           NOT NULL DEFAULT NOW()
);

CREATE TABLE genres (
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    slug VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE user_favorite_genres (
    user_id  BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    genre_id BIGINT NOT NULL REFERENCES genres (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, genre_id)
);

CREATE TABLE songs (
    id         BIGSERIAL PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    artist     VARCHAR(255) NOT NULL,
    album      VARCHAR(255),
    duration   INT,           -- segundos
    cover_url  VARCHAR(500),
    preview_url VARCHAR(500),
    genre_id   BIGINT NOT NULL REFERENCES genres (id),
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE playlists (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    user_id     BIGINT       NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    created_at  TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE playlist_songs (
    playlist_id BIGINT NOT NULL REFERENCES playlists (id) ON DELETE CASCADE,
    song_id     BIGINT NOT NULL REFERENCES songs (id) ON DELETE CASCADE,
    position    INT    NOT NULL DEFAULT 0,
    added_at    TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (playlist_id, song_id)
);

CREATE INDEX idx_songs_genre ON songs (genre_id);
CREATE INDEX idx_playlists_user ON playlists (user_id);
