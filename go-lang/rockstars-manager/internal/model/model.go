package model

type ArtistDto struct {
	ID   int64  `json:"Id"`
	Name string `json:"Name"`
}

type SongDto struct {
	ID     int64  `json:"Id"`
	Name   string `json:"Name"`
	Artist string `json:"Artist"`
}
