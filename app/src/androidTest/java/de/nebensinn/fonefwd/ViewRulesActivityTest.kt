package de.nebensinn.fonefwd

import android.content.Intent
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

val ForwardRule = Rule("param1", "param2")

@MediumTest
@RunWith(AndroidJUnit4::class)
class ViewRulesActivityTest {
    class MyActivityTestRule : ActivityTestRule<ViewRulesActivity>(ViewRulesActivity::class.java) {
        override fun getActivityIntent(): Intent {
            val initialIntent = super.getActivityIntent()
            initialIntent.putExtra(VIEWRULES_EXTRA_NEWRULE, ForwardRule)
            return initialIntent
        }
    }

    @get:Rule
    val activityTestRule = MyActivityTestRule()

    @Test
    fun shouldAddRuleWhenStartedInAddRuleMode() {
        val rulesView = activityTestRule.activity.findViewById(R.id.rulesView) as RecyclerView
        val adapter = rulesView.adapter
        assertEquals(1, adapter.itemCount)
        val firstViewHolder =
                rulesView.findViewHolderForAdapterPosition(0) as RulesViewHolder
        assertEquals("When on param1, forward to param2", firstViewHolder.ruleLabel.text)
        // TODO: add rule to shared preferences and update mainactivity
    }
}