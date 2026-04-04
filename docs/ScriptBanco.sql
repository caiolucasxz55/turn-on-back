-- ScriptBanco.sql
-- DDL para PostgreSQL (local/dev) usado pela aplicação MusicApp / TurnOn
-- Cria tabelas: users, genres, songs, playlists, playlist_songs

CREATE TABLE IF NOT EXISTS genres (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  email VARCHAR(200) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE IF NOT EXISTS songs (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  artist VARCHAR(200),
  genre_id BIGINT REFERENCES genres(id) ON DELETE SET NULL,
  duration_seconds INT,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE IF NOT EXISTS playlists (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(200) NOT NULL,
  user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE IF NOT EXISTS playlist_songs (
  playlist_id BIGINT NOT NULL REFERENCES playlists(id) ON DELETE CASCADE,
  song_id BIGINT NOT NULL REFERENCES songs(id) ON DELETE CASCADE,
  position INT DEFAULT 0,
  added_at TIMESTAMP WITH TIME ZONE DEFAULT now(),
  PRIMARY KEY (playlist_id, song_id)
);

-- Índices comuns
CREATE INDEX IF NOT EXISTS idx_songs_genre ON songs(genre_id);
CREATE INDEX IF NOT EXISTS idx_playlists_user ON playlists(user_id);
