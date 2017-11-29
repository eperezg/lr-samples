package com.liferay.samples.alloy.editor.utils;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * Created by eduardo on 31/10/17.
 */
public class AlloyContributorTestUtil {

    /**
     * This methods checks if a button is present in an array of butons
     *
     * @param buttons -> the array
     * @param name -> the button
     * @return true or false depending if the button exists
     */
    public static boolean checkFeature(JSONArray buttons, String name) {
        if (buttons != null) {
            for (int i = 0; i < buttons.length(); ++i) {
                if (name.equals(buttons.getString(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This path returns the path of a css file
     *
     * @param fileName the filename
     * @param portalUrl the portalUrl
     * @param servletContext the module context
     * @return The path
     */
    public static String generateCssPath(String fileName, String portalUrl, String servletContext) {
        final int stringBundlerCapacity = 8;
        StringBundler sb = new StringBundler(stringBundlerCapacity);

        sb.append("<link href=\"");
        sb.append(portalUrl);
        sb.append(PortalUtil.getPathProxy());
        sb.append(servletContext);
        sb.append("/css/");
        sb.append(fileName);
        sb.append("\" ");
        sb.append(" rel=\"stylesheet\" type=\"text/css\">");

        return sb.toString();
    }

    /**
     * This path returns the path of a js file
     *
     * @param fileName the filename
     * @param portalUrl the portalUrl
     * @param servletContext the module context
     * @return The path
     */
    public static String generateJsPath(String fileName, String portalUrl, String servletContext) {
        final int stringBundlerCapacity = 8;
        StringBundler sb = new StringBundler(stringBundlerCapacity);

        sb.append("<script src=\"");
        sb.append(portalUrl);
        sb.append(PortalUtil.getPathProxy());
        sb.append(servletContext);
        sb.append("/js/");
        sb.append(fileName);
        sb.append("\" ");
        sb.append("type=\"text/javascript\"></script>");

        return sb.toString();
    }

}
