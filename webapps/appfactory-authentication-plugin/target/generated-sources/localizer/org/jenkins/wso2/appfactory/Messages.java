// CHECKSTYLE:OFF

package org.jenkins.wso2.appfactory;

import org.jvnet.localizer.Localizable;
import org.jvnet.localizer.ResourceBundleHolder;

@SuppressWarnings({
    "",
    "PMD"
})
public class Messages {

    private final static ResourceBundleHolder holder = ResourceBundleHolder.get(Messages.class);

    /**
     * Appfactory Authentication
     * 
     */
    public static String DisplayName() {
        return holder.format("DisplayName");
    }

    /**
     * Appfactory Authentication
     * 
     */
    public static Localizable _DisplayName() {
        return new Localizable(holder, "DisplayName");
    }

}
