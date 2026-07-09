package model

import "github.com/google/uuid"

type BoardDto struct {
	ID            uuid.UUID   `json:"id"`
	KalahWashers  []WasherDto `json:"kalahWashers"`
	PlayerOne     *PlayerDto  `json:"playerOne"`
	PlayerTwo     *PlayerDto  `json:"playerTwo"`
	KalahTable    *TableDto   `json:"kalahTable"`
	CurrentPlayer *PlayerDto  `json:"currentPlayer"`
	Winner        *PlayerDto  `json:"winner"`
}

type PlayerDto struct {
	ID   uuid.UUID `json:"id"`
	Name string    `json:"name"`
}

type WasherDto struct {
	ID     uuid.UUID `json:"id"`
	Stones int       `json:"stones"`
}

type TableDto struct {
	ID uuid.UUID `json:"id"`
}
