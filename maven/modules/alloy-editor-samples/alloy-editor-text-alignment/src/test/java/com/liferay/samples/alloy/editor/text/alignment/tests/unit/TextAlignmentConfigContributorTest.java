package com.liferay.samples.alloy.editor.text.alignment.tests.unit;

import com.liferay.portal.json.JSONObjectImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;

import com.liferay.samples.alloy.editor.text.alignment.TextAlignmentConfigContributor;
import com.liferay.samples.alloy.editor.utils.AlloyContributorJsonUtil;
import org.junit.Test;

import static com.liferay.samples.alloy.editor.utils.AlloyContributorTestUtil.checkFeature;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextAlignmentConfigContributorTest {

    private static final String JSON_EXAMPLE_HEADING1 =
            "{" +
            "    toolbars: {" +
            "        styles: {" +
            "            selections: [" +
            "                {" +
            "                    name: 'text'," +
            "                    buttons: ['h1']," +
            "                    test: AlloyEditor.SelectionTest.text" +
            "                }" +
            "            ]" +
            "        }" +
            "    }" +
            "}";

    @Test
    public void populateConfig() throws JSONException {
        JSONObjectImpl json = new JSONObjectImpl(JSON_EXAMPLE_HEADING1);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);

        TextAlignmentConfigContributor textAlignmentConfigContributor = new TextAlignmentConfigContributor();
        assertThat("json doesn't contains paragraphLeft feature yet", !checkFeature(alloyContributorJsonUtil.getTextButtonsArray(),"paragraphLeft"));
        assertThat("json doesn't contains paragraphRight feature yet", !checkFeature(alloyContributorJsonUtil.getTextButtonsArray(),"paragraphRight"));
        assertThat("json doesn't contains paragraphCenter feature yet", !checkFeature(alloyContributorJsonUtil.getTextButtonsArray(),"paragraphCenter"));
        assertThat("json doesn't contains paragraphJustify feature yet", !checkFeature(alloyContributorJsonUtil.getTextButtonsArray(),"paragraphJustify"));

        textAlignmentConfigContributor.populateConfigJSONObject(json, null, null, null);
        assertThat("json contains paragraphLeft feature", checkFeature(alloyContributorJsonUtil.getTextButtonsArray(),"paragraphLeft"));
        assertThat("json contains paragraphRight feature", checkFeature(alloyContributorJsonUtil.getTextButtonsArray(),"paragraphRight"));
        assertThat("json contains paragraphCenter feature", checkFeature(alloyContributorJsonUtil.getTextButtonsArray(),"paragraphCenter"));
        assertThat("json contains paragraphJustify feature", checkFeature(alloyContributorJsonUtil.getTextButtonsArray(),"paragraphJustify"));
    }

}