val avgMinutesBetween: Long = deliveries
    .zipWithNext { a, b ->
        Duration
            .between(a.startedAt, b.startedAt)
            .toMinutes()
    }
    .takeIf { it.isNotEmpty() }
    ?.average()
    ?.roundToLong()
    ?: 0L
