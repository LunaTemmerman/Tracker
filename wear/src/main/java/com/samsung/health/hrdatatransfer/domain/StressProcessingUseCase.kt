package com.samsung.health.hrdatatransfer.domain

import com.samsung.health.hrdatatransfer.data.TrackingRepository
import javax.inject.Inject
import kotlin.math.sqrt

class StressProcessingUseCase @Inject constructor(
    private val trackingRepository: TrackingRepository
) {
    operator fun invoke(thresholdHR: Int = 100, thresholdHRV: Double = 20.0): Boolean {
        val averageHR = trackingRepository.getValidHrData().map { it.hr }.average()
        val ibiList = trackingRepository.getValidHrData().flatMap { it.ibi }

        if (ibiList.isNotEmpty()) {
            val meanIBI = ibiList.average()
            val sumSquaredDiffs = ibiList.map { (it - meanIBI) * (it - meanIBI) }.sum()
            val hrv = sqrt(sumSquaredDiffs / ibiList.size)

            return averageHR > thresholdHR && hrv < thresholdHRV
        }
        return false
    }

}
