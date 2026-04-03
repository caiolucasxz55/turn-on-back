# 🎵 MusicApp API - Guia de Endpoints

**Base URL:** `https://turnon-app-cp5.azurewebsites.net`

---

## 📌 Autenticação

### Rotas Públicas (sem token)
- `POST /auth/register`
- `POST /auth/login`
- `GET /genres`

### Rotas Protegidas (exigem token JWT)
Adicione o header:
```
Authorization: Bearer <seu_token>
```

---

## 🔐 Autenticação

### 1. Registrar novo usuário
**POST** `/auth/register`

**Body:**
```json
{
  "name": "João Silva",
  "email": "joao@example.com",
  "password": "123456"
}
```

**Response (201 Created):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "name": "João Silva",
    "email": "joao@example.com"
  }
}
```

---

### 2. Fazer login
**POST** `/auth/login`

**Body:**
```json
{
  "email": "joao@example.com",
  "password": "123456"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "name": "João Silva",
    "email": "joao@example.com"
  }
}
```

**⚠️ IMPORTANTE:** Guarde o `token` para usar nos próximos endpoints

---

## 👤 Usuário

### 1. Obter perfil do usuário (autenticado)
**GET** `/users/me`

**Headers:**
```
Authorization: Bearer <token>
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@example.com",
  "favoriteGenres": [1, 2, 3]
}
```

---

### 2. Atualizar perfil do usuário
**PUT** `/users/me`

**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Body:**
```json
{
  "name": "João Pedro Silva"
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "João Pedro Silva",
  "email": "joao@example.com",
  "favoriteGenres": [1, 2, 3]
}
```

---

### 3. Atualizar gêneros favoritos
**PUT** `/users/me/genres`

**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Body:**
```json
{
  "genreIds": [1, 2, 4, 5]
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "João Pedro Silva",
  "email": "joao@example.com",
  "favoriteGenres": [1, 2, 4, 5]
}
```

---

## 🎵 Gêneros

### Listar todos os gêneros (público)
**GET** `/genres`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Rock"
  },
  {
    "id": 2,
    "name": "Pop"
  },
  {
    "id": 3,
    "name": "Jazz"
  }
]
```

---

## 🎶 Músicas

### 1. Listar todas as músicas
**GET** `/songs`

**Headers:**
```
Authorization: Bearer <token>
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "title": "Bohemian Rhapsody",
    "artist": "Queen",
    "genre": "Rock"
  },
  {
    "id": 2,
    "title": "Imagine",
    "artist": "John Lennon",
    "genre": "Pop"
  }
]
```

---

### 2. Listar músicas por gênero
**GET** `/songs?genre=Rock`

**Headers:**
```
Authorization: Bearer <token>
```

**Query Parameters:**
- `genre` (opcional): filtrar por nome do gênero

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "title": "Bohemian Rhapsody",
    "artist": "Queen",
    "genre": "Rock"
  }
]
```

---

### 3. Obter música por ID
**GET** `/songs/{id}`

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `id`: ID da música

**Response (200 OK):**
```json
{
  "id": 1,
  "title": "Bohemian Rhapsody",
  "artist": "Queen",
  "genre": "Rock"
}
```

---

### 4. Obter recomendações de músicas
**GET** `/songs/recommended`

**Headers:**
```
Authorization: Bearer <token>
```

**Response (200 OK):**
```json
[
  {
    "id": 5,
    "title": "Stairway to Heaven",
    "artist": "Led Zeppelin",
    "genre": "Rock"
  }
]
```

---

## 📚 Playlists (CRUD Completo)

### 1. Listar todas as playlists do usuário
**GET** `/playlists`

**Headers:**
```
Authorization: Bearer <token>
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Rock Clássico",
    "description": "Melhores do rock",
    "songs": [
      {
        "id": 1,
        "title": "Bohemian Rhapsody",
        "artist": "Queen"
      }
    ]
  }
]
```

---

### 2. Obter playlist por ID
**GET** `/playlists/{id}`

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `id`: ID da playlist

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "Rock Clássico",
  "description": "Melhores do rock",
  "songs": [
    {
      "id": 1,
      "title": "Bohemian Rhapsody",
      "artist": "Queen"
    },
    {
      "id": 2,
      "title": "Stairway to Heaven",
      "artist": "Led Zeppelin"
    }
  ]
}
```

---

### 3. Criar nova playlist
**POST** `/playlists`

**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Body:**
```json
{
  "name": "Minha Playlist",
  "description": "Som para estudar"
}
```

**Response (201 Created):**
```json
{
  "id": 5,
  "name": "Minha Playlist",
  "description": "Som para estudar",
  "songs": []
}
```

---

### 4. Atualizar playlist
**PUT** `/playlists/{id}`

**Headers:**
```
Authorization: Bearer <token>
Content-Type: application/json
```

**Path Parameters:**
- `id`: ID da playlist

**Body:**
```json
{
  "name": "Playlist Atualizada",
  "description": "Nova descrição"
}
```

**Response (200 OK):**
```json
{
  "id": 5,
  "name": "Playlist Atualizada",
  "description": "Nova descrição",
  "songs": []
}
```

---

### 5. Adicionar música à playlist
**POST** `/playlists/{id}/songs/{songId}`

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `id`: ID da playlist
- `songId`: ID da música

**Response (200 OK):**
```json
{
  "id": 5,
  "name": "Playlist Atualizada",
  "description": "Nova descrição",
  "songs": [
    {
      "id": 1,
      "title": "Bohemian Rhapsody",
      "artist": "Queen"
    }
  ]
}
```

---

### 6. Remover música da playlist
**DELETE** `/playlists/{id}/songs/{songId}`

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `id`: ID da playlist
- `songId`: ID da música

**Response (200 OK):**
```json
{
  "id": 5,
  "name": "Playlist Atualizada",
  "description": "Nova descrição",
  "songs": []
}
```

---

### 7. Deletar playlist
**DELETE** `/playlists/{id}`

**Headers:**
```
Authorization: Bearer <token>
```

**Path Parameters:**
- `id`: ID da playlist

**Response (204 No Content)**

---

## 🌐 Busca Externa (iTunes API)

### Buscar músicas na iTunes
**GET** `/external/songs/search`

**Headers:**
```
Authorization: Bearer <token>
```

**Query Parameters:**
- `term` (obrigatório): termo de busca
- `limit` (opcional, padrão: 10): quantidade de resultados

**Exemplo:**
```
GET /external/songs/search?term=Beatles&limit=5
```

**Response (200 OK):**
```json
{
  "results": [
    {
      "trackName": "Let It Be",
      "artistName": "The Beatles",
      "collectionName": "Let It Be",
      "artworkUrl100": "..."
    }
  ],
  "resultCount": 5
}
```

---

## 📋 Sequência de Testes (curl)

### 1. Registrar
```bash
curl -X POST https://turnon-app-cp5.azurewebsites.net/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@example.com",
    "password": "123456"
  }'
```

### 2. Login (anote o token)
```bash
curl -X POST https://turnon-app-cp5.azurewebsites.net/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@example.com",
    "password": "123456"
  }'
```

### 3. Pegar perfil
```bash
curl -X GET https://turnon-app-cp5.azurewebsites.net/users/me \
  -H "Authorization: Bearer TOKEN_AQUI"
```

### 4. Listar gêneros (público)
```bash
curl -X GET https://turnon-app-cp5.azurewebsites.net/genres
```

### 5. Listar músicas
```bash
curl -X GET https://turnon-app-cp5.azurewebsites.net/songs \
  -H "Authorization: Bearer TOKEN_AQUI"
```

### 6. Criar playlist
```bash
curl -X POST https://turnon-app-cp5.azurewebsites.net/playlists \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TOKEN_AQUI" \
  -d '{
    "name": "Meu Rock",
    "description": "Clássicos"
  }'
```

### 7. Adicionar música na playlist
```bash
curl -X POST https://turnon-app-cp5.azurewebsites.net/playlists/PLAYLIST_ID/songs/SONG_ID \
  -H "Authorization: Bearer TOKEN_AQUI"
```

### 8. Atualizar playlist
```bash
curl -X PUT https://turnon-app-cp5.azurewebsites.net/playlists/PLAYLIST_ID \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TOKEN_AQUI" \
  -d '{
    "name": "Meu Rock Favorito",
    "description": "Atualizado"
  }'
```

### 9. Deletar música da playlist
```bash
curl -X DELETE https://turnon-app-cp5.azurewebsites.net/playlists/PLAYLIST_ID/songs/SONG_ID \
  -H "Authorization: Bearer TOKEN_AQUI"
```

### 10. Deletar playlist
```bash
curl -X DELETE https://turnon-app-cp5.azurewebsites.net/playlists/PLAYLIST_ID \
  -H "Authorization: Bearer TOKEN_AQUI"
```

---

## 📋 Configuração Postman (Variáveis de Ambiente)

Crie uma **Environment** com as seguintes variáveis:

| Variável | Valor | Tipo |
|----------|-------|------|
| `server` | `https://turnon-app-cp5.azurewebsites.net` | string |
| `token` | *(preenchido após login)* | string |
| `userId` | *(preenchido após login)* | string |
| `playlistId` | *(preenchido após criar playlist)* | string |

### Scripts Postman

**Na requisição de Login, aba Tests:**
```javascript
var jsonData = pm.response.json();
pm.environment.set("token", jsonData.token);
pm.environment.set("userId", jsonData.user.id);
```

**Na requisição de Criar Playlist, aba Tests:**
```javascript
var jsonData = pm.response.json();
pm.environment.set("playlistId", jsonData.id);
```

### Reutilizar variáveis
Use `{{server}}`, `{{token}}`, `{{playlistId}}` nas URLs e headers:

```
GET {{server}}/users/me
Authorization: Bearer {{token}}
```

---

## ✅ Checklist de Testes Completo

- [ ] `POST /auth/register` - Registrar novo usuário
- [ ] `POST /auth/login` - Login e obter token
- [ ] `GET /users/me` - Obter perfil
- [ ] `PUT /users/me` - Atualizar nome
- [ ] `PUT /users/me/genres` - Atualizar gêneros favoritos
- [ ] `GET /genres` - Listar gêneros
- [ ] `GET /songs` - Listar músicas
- [ ] `GET /songs?genre=Rock` - Filtrar por gênero
- [ ] `GET /songs/{id}` - Obter música específica
- [ ] `GET /songs/recommended` - Obter recomendações
- [ ] `GET /playlists` - Listar playlists
- [ ] `POST /playlists` - Criar playlist
- [ ] `GET /playlists/{id}` - Obter playlist
- [ ] `POST /playlists/{id}/songs/{songId}` - Adicionar música
- [ ] `PUT /playlists/{id}` - Atualizar playlist
- [ ] `DELETE /playlists/{id}/songs/{songId}` - Remover música
- [ ] `DELETE /playlists/{id}` - Deletar playlist
- [ ] `GET /external/songs/search?term=Beatles` - Buscar na iTunes

---

**Data de Atualização:** 03/04/2026  
**Versão da API:** 1.0
