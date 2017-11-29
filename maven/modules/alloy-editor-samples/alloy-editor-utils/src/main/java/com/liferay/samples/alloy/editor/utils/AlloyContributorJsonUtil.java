package com.liferay.samples.alloy.editor.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Objects;

/**
 * This class receives a Json object containing the alloy editor information and let us modify it.
 * @author eperezg
 */
public class AlloyContributorJsonUtil {

    /** JSONObject the classes uses to keep track of added elements. **/
    private JSONObject json;

    private static final String TOOLBARS = "toolbars";
    private static final String ADD = "add";
    private static final String STYLES = "styles";
    private static final String SELECTIONS = "selections";
    private static final String ATTRIBUTE_NAME = "name";
    private static final String ATTRIBUTE_BUTTONS = "buttons";
    private static final String VALUE_TEXT = "text";
    private static final String VALUE_STYLES = "styles";

    /** Log object that prints to the log. **/
    private Log log = LogFactoryUtil.getLog(AlloyContributorJsonUtil.class);

    /**
     * Copy the JSON object or create if null.
     *
     * @param json -> the alloy editor json object.
     */
    public AlloyContributorJsonUtil(JSONObject json) {
        if (json == null) {
            this.json = JSONFactoryUtil.createJSONObject();
        } else {
            log.debug("Initial JSON: " + json);
            this.json = json;
        }
    }

    /**
     * Getter for param.
     * @return json.
     */
    public JSONObject getJson() {
        return json;
    }

    /**
     * Method that gets the toolbars element from the json.
     *
     * @return the toolbars element.
     */
    public JSONObject getToolbars() {
        JSONObject toolbars = json.getJSONObject(TOOLBARS);
        if (toolbars == null) {
            toolbars = JSONFactoryUtil.createJSONObject();
            json.put(TOOLBARS, toolbars);
        }
        return toolbars;
    }

    /**
     * Method that gets the toolbars.styles element from the json.
     *
     * @return toolbars.styles element.
     */
    public JSONObject getStyles() {
        JSONObject styles = getToolbars().getJSONObject(STYLES);
        if (styles == null) {
            styles = JSONFactoryUtil.createJSONObject();
            getToolbars().put(STYLES, styles);
        }
        return styles;
    }

    /**
     * Method that gets the toolbars.add element from the json.
     *
     * @return toolbars.add element.
     */
    public JSONObject getAdd() {
        JSONObject add = getToolbars().getJSONObject(ADD);
        if (add == null) {
            add = JSONFactoryUtil.createJSONObject();
            getToolbars().put(ADD, add);
        }
        return add;
    }

    /**
     * Method that gets the toolbars.styles.selections array from the json.
     *
     * @return toolbars.styles.selections array.
     */
    public JSONArray getSelections() {
        JSONArray selections = getStyles().getJSONArray(SELECTIONS);
        if (selections == null) {
            selections = JSONFactoryUtil.createJSONArray();
            getStyles().put(SELECTIONS, selections);
        }
        return selections;
    }

    /**
     * Method that returns the selection element which name mathches the param name.
     *
     * @param name -> the name of the selection.
     * @return the selection element which name matches @name, otherwise null.
     */
    public JSONObject getSelection(String name) {
        for (int i = 0; i < getSelections().length(); i++) {
            JSONObject selection = getSelections().getJSONObject(i);

            if (Objects.equals(selection.get(ATTRIBUTE_NAME), name)) {
                return selection;
            }
        }
        return null;
    }

    /**
     * Method that returns the selection element which name is "text".
     *
     * @return the selection element which name is "text", otherwise null.
     */
    public JSONObject getTextSelection() {
        return getSelection(VALUE_TEXT);
    }

    /**
     * Method that creates a new selection with the given name.
     *
     * @param name -> the name of the selection.
     * @return the new selection.
     */
    public JSONObject addSelection(String name) {
        JSONObject selection = getSelection(name);
        if (selection != null) {
            return selection;
        } else {
            selection = new JSONFactoryUtil().createJSONObject();
            selection.put("name", name);
            selection.put("buttons", new JSONFactoryUtil().createJSONArray());
            getSelections().put(selection);
            return selection;
        }
    }

    /**
     * Method that creates a new selection with the given name and test function.
     *
     * @param name -> the name of the selection.
     * @param test -> the name of the test function.
     * @return the new selection.
     */
    public JSONObject addSelection(String name, String test) {
        JSONObject selection = addSelection(name);
        selection.put("test", test);
        return selection;
    }

    /**
     * Method that returns the buttons array from the selection with the given name.
     *
     * @param selectionName -> the name of the section.
     * @return buttons array belonging to the selection that matches the given name.
     */
    public JSONArray getButtonsArray(String selectionName) {
        JSONObject selection = getSelection(selectionName);

        if (selection != null) {
            JSONArray buttons = selection.getJSONArray(ATTRIBUTE_BUTTONS);
            log.debug(ATTRIBUTE_BUTTONS + ": " + buttons.toString());
            return buttons;
        }
        return null;
    }

    /**
     * Method that returns the buttons array from the selection with the "text" name.
     *
     * @return buttons array belonging to the selection that matches the "text" name
     */
    public JSONArray getTextButtonsArray() {
        return getButtonsArray(VALUE_TEXT);
    }

    /**
     * Method that returns the buttons array from the "add" section.
     *
     * @return buttons array belonging to the "add" section.
     */
    public JSONArray getAddButtonsArray() {
        JSONObject addSection = getAdd();

        JSONArray buttons = addSection.getJSONArray(ATTRIBUTE_BUTTONS);
        log.debug(ATTRIBUTE_BUTTONS + ": " + buttons);

        return buttons;
    }

    /**
     * Method that gets the button element which a given name on the "text" selection.
     *
     * @param buttonName -> the name of the button.
     * @return the button element which name matches the @buttonName in the "text" selection.
     */
    public JSONObject getTextStyleButton(String buttonName) {

        JSONArray buttons = getButtonsArray(VALUE_TEXT);
        if (buttons != null) {
            for (int i = 0; i < buttons.length(); i++) {
                JSONObject button = buttons.getJSONObject(i);
                if (Objects.equals(button.get(ATTRIBUTE_NAME), buttonName)) {
                    return button;
                }
            }
        }
        return null;
    }

    /**
     * Method that includes a feature in the given selection.
     *
     * @param selection -> the selection.
     * @param feature -> the feature
     * @return true if feature was added, otherwise false
     */
    public boolean addFeature(String selection, String feature) {
        JSONArray buttons = getButtonsArray(selection);
        if (buttons != null) {
            buttons.put(feature);
            return true;
        }
        return false;
    }

    /**
     * Method that removes a feature in the given selection.
     *
     * @param selectionName -> the selection.
     * @param feature -> the feature
     */
    public void removeFeature(String selectionName, String feature) {
        JSONArray buttons = getButtonsArray(selectionName);
        if (buttons != null) {
            JSONArray newButtons = JSONFactoryUtil.createJSONArray();
            for (int i = 0; i < buttons.length(); ++i) {
                String button = buttons.getString(i);
                if (!button.equals(feature)) {
                    newButtons.put(button);
                }
            }
            JSONObject selection = getSelection(selectionName);
            selection.put("buttons", newButtons);
        }
    }

    /**
     * Method that includes a feature in the "text" selection.
     *
     * @param feature -> the feature
     * @return true if feature was added, otherwise false
     */
    public boolean addTextFeature(String feature) {
        JSONArray buttons = getTextButtonsArray();
        if (buttons != null) {
            buttons.put(feature);
            return true;
        }
        return false;
    }

    /**
     * Method that adds a feature to the "add" section.
     *
     * @param feature -> the feature
     * @return true if th button was added, otherwise false
     */
    public boolean addAddFeature(String feature) {
        JSONArray buttons = getAddButtonsArray();
        if (buttons != null) {
            buttons.put(feature);
            return true;
        }
        return false;
    }

    /**
     * Method that adds an style to the text style combo.
     *
     * @param myStyle -> a Json containing the style node
     * @return true if style was added, otherwise false
     */
    public boolean addTextStyle(JSONObject myStyle) {

        JSONObject stylesButton = getTextStyleButton(VALUE_STYLES);
        if (stylesButton != null) {
            JSONObject cfg = stylesButton.getJSONObject("cfg");
            if (cfg != null) {
                log.debug("cfg: " + cfg.toString());
                JSONArray styles = cfg.getJSONArray(STYLES);
                log.debug("styles: " + styles.toString());
                styles.put(myStyle);
                return true;
            }
        }
        return false;
    }


}
