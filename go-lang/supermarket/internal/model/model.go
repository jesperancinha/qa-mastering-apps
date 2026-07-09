package model

import (
	"time"
)

type DeliveryStatus string

const (
	IN_PROGRESS DeliveryStatus = "IN_PROGRESS"
	DELIVERED   DeliveryStatus = "DELIVERED"
)

type DeliveryRequestDto struct {
	VehicleID  string         `json:"vehicleId"`
	Address    string         `json:"address"`
	StartedAt  time.Time      `json:"startedAt"`
	FinishedAt *time.Time     `json:"finishedAt"`
	Status     DeliveryStatus `json:"status"`
}

type DeliveryResponseDto struct {
	ID         string         `json:"id"`
	VehicleID  string         `json:"vehicleId"`
	Address    string         `json:"address"`
	StartedAt  time.Time      `json:"startedAt"`
	FinishedAt *time.Time     `json:"finishedAt"`
	Status     DeliveryStatus `json:"status"`
}

type InvoiceRequestDto struct {
	DeliveryIDs []string `json:"deliveryIds"`
}

type InvoiceResponseDto struct {
	DeliveryID string `json:"deliveryId"`
	InvoiceID  string `json:"invoiceId"`
}

type DeliveriesSummaryDto struct {
	Deliveries                         int   `json:"deliveries"`
	AverageMinutesBetweenDeliveryStart int64 `json:"averageMinutesBetweenDeliveryStart"`
}
