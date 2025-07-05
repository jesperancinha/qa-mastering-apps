val avgMinutesBetween: Long = if (count < 2) {
    0L
} else {
    val durations = deliveries
        .zipWithNext { a, b ->
            Duration
                .between(
                    a.startedAt, b.startedAt)
                .toMinutes()
    }
    durations.average().roundToLong()
}
