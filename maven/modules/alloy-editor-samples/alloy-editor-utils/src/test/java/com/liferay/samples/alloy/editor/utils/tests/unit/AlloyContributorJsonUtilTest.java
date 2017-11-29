package com.liferay.samples.alloy.editor.utils.tests.unit;

import com.liferay.portal.json.JSONArrayImpl;
import com.liferay.portal.json.JSONObjectImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import com.liferay.samples.alloy.editor.utils.AlloyContributorJsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({JSONFactoryUtil.class})
public class AlloyContributorJsonUtilTest {

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

    private static final String JSON_EXAMPLE_STYLES =
            "{" +
            "    toolbars: {" +
            "        styles: {" +
            "            selections: [" +
            "                {" +
            "                    name: 'text'," +
            "                    buttons: [{" +
            "                        name: 'styles'," +
            "                        cfg: {" +
            "                            styles: [" +
            "                                {" +
            "                                    name: 'Head 1'," +
            "                                    style: { element: 'h1' }" +
            "                                }," +
            "                                {" +
            "                                    name: 'Head 2'," +
            "                                    style: { element: 'h2' }" +
            "                                }," +
            "                                {" +
            "                                    name: 'Big'," +
            "                                    style: { element: 'big' }" +
            "                                }," +
            "                                {" +
            "                                    name: 'Small'," +
            "                                    style: { element: 'small' }" +
            "                                }" +
            "                            ]" +
            "                        }" +
            "                    }]," +
            "                    test: AlloyEditor.SelectionTest.text" +
            "                }" +
            "            ]" +
            "        }" +
            "    }" +
            "}";

    private static final String CODE_STYLE =
            "{" +
            "   name: 'Code'," +
            "   style: { element: 'code' }" +
            "}";

    private final String JSON_EMPTY_SELECTIONS=
            "{" +
            "    toolbars: {" +
            "        styles: {" +
            "            selections: [" +
            "            ]" +
            "        }" +
            "    }" +
            "}";

    public final String ADD_AND_STYLE =
            "{" +
            "   toolbars: {" +
            "       add: {"+
            "           buttons: ['image', 'camera', 'hline', 'table']," +
            "           tabIndex: 2" +
            "       }," +
            "       styles: {" +
            "           selections: AlloyEditor.Selections," +
            "           tabIndex: 1" +
            "       }" +
            "   }" +
            "}";

    @Before
    public void prepareMocks() throws Exception {
        //PortalRuntimePermission.checkGetBeanProperty(JSONFactoryUtil.class);

        PowerMockito.mockStatic(JSONFactoryUtil.class);
        when(JSONFactoryUtil.createJSONObject()).thenReturn(new JSONObjectImpl());

        when(JSONFactoryUtil.createJSONArray()).thenReturn(new JSONArrayImpl());

    }

    @Test
    public void publicConstructorWithJson() {
        JSONObject json = new JSONObjectImpl();
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);
        assertEquals("Json object is the same",json, alloyContributorJsonUtil.getJson());

        alloyContributorJsonUtil = new AlloyContributorJsonUtil(null);
        assertThat("Json object is also initialized when receives a null one", new JSONObjectImpl().toString().equals(alloyContributorJsonUtil.getJson().toString()));
    }

    @Test
    public void getToolbars() throws JSONException {
        JSONObject json = new JSONObjectImpl(JSON_EXAMPLE_HEADING1);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);

        assertThat("getToolbars method returns the toolbars node", alloyContributorJsonUtil.getToolbars().toString().equals(json.getJSONObject("toolbars").toString()));

        alloyContributorJsonUtil = new AlloyContributorJsonUtil(null);
        assertThat("getToolbars method creates an object if there is no toolbars node", alloyContributorJsonUtil.getToolbars() != null);
    }

    @Test
    public void getStyles() throws JSONException {
        JSONObject json = new JSONObjectImpl(JSON_EXAMPLE_HEADING1);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);

        assertThat("getStyles method returns the styles node inside toolbars", alloyContributorJsonUtil.getStyles().toString().equals(json.getJSONObject("toolbars").getJSONObject("styles").toString()));

        alloyContributorJsonUtil = new AlloyContributorJsonUtil(null);
        assertThat("getStyles method creates an object if there is no styles node", alloyContributorJsonUtil.getStyles() != null);
    }

    @Test
    public void getAdd() throws JSONException {
        JSONObject json = new JSONObjectImpl(ADD_AND_STYLE);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);

        assertThat("getAdd method returns the add node inside toolbars", alloyContributorJsonUtil.getAdd().toString().equals(json.getJSONObject("toolbars").getJSONObject("add").toString()));

        alloyContributorJsonUtil = new AlloyContributorJsonUtil(null);
        assertThat("getAdd method creates an object if there is no add node", alloyContributorJsonUtil.getAdd() != null);
    }

    @Test
    public void getSelections() throws JSONException {
        JSONObject json = new JSONObjectImpl(JSON_EXAMPLE_HEADING1);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);

        assertThat("getSelections method returns the selections array inside styles", alloyContributorJsonUtil.getSelections().toString().equals(json.getJSONObject("toolbars").getJSONObject("styles").getJSONArray("selections").toString()));

        alloyContributorJsonUtil = new AlloyContributorJsonUtil(null);
        assertThat("getSelections method creates an array if there is no selections node", alloyContributorJsonUtil.getSelections() != null);
    }

    @Test
    public void getSelection() throws JSONException {
        JSONObject json = new JSONObjectImpl(JSON_EXAMPLE_HEADING1);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);
        assertThat("getSelection method returns the element inside the selections array that match the name", alloyContributorJsonUtil.getSelection("text") != null);
        assertThat("getSelection return null if there is no element with the given name", alloyContributorJsonUtil.getSelection("fakeName") == null);
    }

    @Test
    public void getTextSelection() throws JSONException {
        JSONObject json = new JSONObjectImpl((JSON_EXAMPLE_HEADING1));
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);

        String expected = "{\"buttons\":[\"h1\"],\"test\":\"AlloyEditor.SelectionTest.text\",\"name\":\"text\"}";
        String getTextResult = alloyContributorJsonUtil.getTextSelection().toString();
        String getByNameTextResult = alloyContributorJsonUtil.getSelection("text").toString();

        assertThat("getText returns expected", expected.equals(getTextResult));
        assertThat("also by name is the same", getTextResult.equals(getByNameTextResult));

    }

    @Test
    public void addSelection() throws JSONException {
        JSONObject json = new JSONObjectImpl((JSON_EXAMPLE_HEADING1));
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);
        int initialSelections = alloyContributorJsonUtil.getSelections().length();

        String mySelection = "mySelection";
        String myTest = "AlloyEditor.SelectionTest.dropdown";
        alloyContributorJsonUtil.addSelection(mySelection, myTest);

        assertThat("initial selections number has been increased in 1 element", alloyContributorJsonUtil.getSelections().length() == (initialSelections + 1));
        JSONObject selection = alloyContributorJsonUtil.getSelection(mySelection);
        assertThat("selection is not null", selection != null);
        assertThat("the selection test is the same we introduced", selection.getString("test").equals(myTest));

        alloyContributorJsonUtil.addSelection(mySelection);
        assertThat("when we add a selection that already exists, we keep its nodes", selection.getString("test").equals(myTest));

        alloyContributorJsonUtil.addSelection(mySelection, "newTest");
        assertThat("when we add a selection that already exists, if we change the test it is updated", selection.getString("test").equals("newTest"));
    }


    @Test
    public void getTextButtonsArray() throws JSONException {
        JSONObject json = new JSONObjectImpl(JSON_EXAMPLE_HEADING1);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);
        System.out.println(alloyContributorJsonUtil.getTextButtonsArray().toString());
        assertEquals("JsonButtonsArray returns the buttons array in the node which name is text","[\"h1\"]", alloyContributorJsonUtil.getTextButtonsArray().toString());
    }

    @Test
    public void getAddButtonsArray() throws JSONException {
        JSONObject json = new JSONObjectImpl(ADD_AND_STYLE);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);
        assertEquals("JsonButtonsArray returns the buttons array in the node which name is text","[\"image\",\"camera\",\"hline\",\"table\"]", alloyContributorJsonUtil.getAddButtonsArray().toString());
    }

    @Test
    public void getEmptyButtons() throws JSONException {
        JSONObject json = new JSONObjectImpl(JSON_EMPTY_SELECTIONS);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);
        assertThat("There are not buttons", alloyContributorJsonUtil.getTextButtonsArray() == null);
        assertThat("There isn't any button which name is h1", alloyContributorJsonUtil.getTextStyleButton("h1") == null);
    }

    @Test
    public void addTextFeature() throws JSONException {
        JSONObject json = new JSONObjectImpl(JSON_EXAMPLE_HEADING1);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);
        alloyContributorJsonUtil.addTextFeature("table");
        assertEquals("Json object is not the same","table", alloyContributorJsonUtil.getTextButtonsArray().getString(1));
    }

    @Test
    public void addTextStyle() throws JSONException {
        JSONObject json = new JSONObjectImpl(JSON_EXAMPLE_STYLES);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);

        JSONObject textStyleJson = new JSONObjectImpl(CODE_STYLE);
        alloyContributorJsonUtil.addTextStyle(textStyleJson);

        assertEquals("Style is not contained",
                textStyleJson.toString(),
                alloyContributorJsonUtil.getTextStyleButton("styles").getJSONObject("cfg").getJSONArray("styles").getJSONObject(4).toString());
    }

    @Test
    public void addElementsToIncorrectJson() throws JSONException {
        JSONObject json = new JSONObjectImpl(JSON_EMPTY_SELECTIONS);
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);
        assertThat("It's not possible to add feature", alloyContributorJsonUtil.addTextFeature("x") == false);
        assertThat("It's not possible to add style", alloyContributorJsonUtil.addTextStyle(json) == false);
    }

    @Test
    public void addAddFeature() throws JSONException {
        JSONObject json = new JSONObjectImpl((ADD_AND_STYLE));
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);

        String buttonName = "myCustomButton";

        assertThat("my button doesn't exists yet", !checkFeature(alloyContributorJsonUtil.getAddButtonsArray(), buttonName));

        alloyContributorJsonUtil.addAddFeature(buttonName);
        assertThat("my button exists now", checkFeature(alloyContributorJsonUtil.getAddButtonsArray(), buttonName));

        json = new JSONObjectImpl((JSON_EXAMPLE_STYLES));
        alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);
        assertThat("if there is no buttons array on add section my button is no added", alloyContributorJsonUtil.addAddFeature(buttonName) == false);
    }

    @Test
    public void addFeature() throws JSONException {
        JSONObject json = new JSONObjectImpl((JSON_EXAMPLE_STYLES));
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);

        String buttonName = "myCustomButton";
        String selectionName = "text";

        assertThat("my button doesn't exists yet", !checkFeature(alloyContributorJsonUtil.getButtonsArray(selectionName), buttonName));

        alloyContributorJsonUtil.addFeature(selectionName, buttonName);
        assertThat("my button exists now", checkFeature(alloyContributorJsonUtil.getButtonsArray(selectionName), buttonName));

        assertThat("it's not possible to add a feature to an unexisting selection", alloyContributorJsonUtil.addFeature("fakeSelection", buttonName) == false);
    }

    @Test
    public void removeFeature() throws JSONException {
        JSONObject json = new JSONObjectImpl((JSON_EXAMPLE_HEADING1));
        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(json);

        String selectionName = "text";
        JSONArray buttons = alloyContributorJsonUtil.getButtonsArray(selectionName);
        int initialSize = buttons.length();
        if (initialSize > 0) {
            String firstButton = buttons.getString(0);
            assertThat("The first button exists", checkFeature(alloyContributorJsonUtil.getButtonsArray(selectionName), firstButton));

            alloyContributorJsonUtil.removeFeature(selectionName, firstButton);
            assertThat("The number of buttons has decreased in 1", alloyContributorJsonUtil.getButtonsArray(selectionName).length() == initialSize - 1);
            assertThat("The first button doesn't exist", !checkFeature(alloyContributorJsonUtil.getButtonsArray(selectionName), firstButton));

        }
    }

    private boolean checkFeature(JSONArray buttons, String name) {
        for (int i = 0; i < buttons.length(); ++i) {
            if (name.equals(buttons.getString(i))) {
                return true;
            }
        }
        return false;
    }
}
