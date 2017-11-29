package com.liferay.samples.alloy.editor.text.alignment;

import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.Map;

import com.liferay.samples.alloy.editor.utils.AlloyContributorJsonUtil;
import org.osgi.service.component.annotations.Component;

@Component(
    property = {"editor.name=alloyeditor", "service.ranking:Integer=1000"},
    service = EditorConfigContributor.class
)
public class TextAlignmentConfigContributor
    extends BaseEditorConfigContributor {

    @Override
    public void populateConfigJSONObject(
        JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
        ThemeDisplay themeDisplay,
        RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

        AlloyContributorJsonUtil alloyContributorJsonUtil = new AlloyContributorJsonUtil(jsonObject);
        alloyContributorJsonUtil.addTextFeature("paragraphLeft");
        alloyContributorJsonUtil.addTextFeature("paragraphRight");
        alloyContributorJsonUtil.addTextFeature("paragraphCenter");
        alloyContributorJsonUtil.addTextFeature("paragraphJustify");
    }
}
