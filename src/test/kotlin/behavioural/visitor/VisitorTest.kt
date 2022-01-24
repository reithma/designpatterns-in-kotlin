package behavioural.visitor

import org.junit.Test
import kotlin.test.assertEquals

class VisitorTest {
    @Test
    fun `test visitor functionality`() {
        val projectAlpha = FixedPriceContract(costPerYear = 10_000)
        val projectBeta = SupportContract(costPerMonth = 500)
        val projectGamma = TimeAndMaterialsContract(hours = 10, costPerHour = 150)
        val projectDelta = TimeAndMaterialsContract(hours = 50, costPerHour = 50)

        val projects = arrayListOf(projectAlpha, projectBeta, projectGamma, projectDelta)

        val monthlyCostVisitor = MonthlyCostReportVisitor()
        val monthlyCosts = projects.sumOf { it.accept(monthlyCostVisitor) }
        assertEquals(monthlyCosts, 5333L)

        val yearlyCostVisitor  = YearlyCostReportVisitor()
        val yearlyCosts = projects.sumOf { it.accept(yearlyCostVisitor) }
        assertEquals(yearlyCosts, 20000)
    }
}
