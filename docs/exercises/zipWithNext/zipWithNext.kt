val avgMinutesBetween: Long = deliveries
    .zipWithNext { a, b ->
        Duration
            .between(a.startedAt, b.startedAt)
            .toMinutes()
            .toDouble()
    }
    .takeIf { it.isNotEmpty() }
    ?.average()
    ?.roundToLong()
    ?: 0L
