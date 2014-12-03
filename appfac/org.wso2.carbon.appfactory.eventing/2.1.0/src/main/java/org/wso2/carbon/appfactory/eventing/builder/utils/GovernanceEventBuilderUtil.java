package org.wso2.carbon.appfactory.eventing.builder.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appfactory.eventing.AppFactoryEventException;
import org.wso2.carbon.appfactory.eventing.Event;
import org.wso2.carbon.appfactory.eventing.Event.Category;
import org.wso2.carbon.appfactory.eventing.EventNotifier;

public class GovernanceEventBuilderUtil {


    private static Log log = LogFactory.getLog(GovernanceEventBuilderUtil.class);

    public static void invokePromoteEvents(String appId, String promotedBy, String title, String description, String status) {
        Category eventStatus;
        if (status.equals("INFO")) {
            eventStatus = Category.INFO;
        } else {
            eventStatus = Category.ERROR;
        }
        try {
            EventNotifier.getInstance().notify(GovernanceEventBuilderUtil.buildPromoteEvents(appId, promotedBy, title, description, eventStatus));
        } catch (AppFactoryEventException e) {
            log.error("Failed to notify application promote event.", e);
        }
    }


    /**
     *
     * @param appId application key
     * @param promotedBy username of the user who performed promote action
     * @param title notification title
     * @param description notification description
     * @param status notification category whether it is SUCCESS or ERROR
     * @return event triggered when the promote operation is invoked
     */
    public static Event buildPromoteEvents(String appId, String promotedBy, String title, String description, Category status) {
        Event event = new Event();
        Event.EventDispatchType[] eventDispatchTypes;

        if (status == Category.INFO) {
            eventDispatchTypes =
                    new Event.EventDispatchType[]{Event.EventDispatchType.SOCIAL_ACTIVITY};
        } else {

            eventDispatchTypes =
                    new Event.EventDispatchType[]{
                            Event.EventDispatchType.SOCIAL_ACTIVITY,
                            Event.EventDispatchType.GUARANTEED_DELIVERY};
        }

        event.setEventDispatchTypes(eventDispatchTypes);
        event.setSender(promotedBy);
        event.setCategory(status);
        event.setTarget(appId);
        event.setMessageBody(description);
        event.setMessageTitle(title);
        return event;
    }

    /**
     *
     * @param appId application key
     * @param checkedBy user who clicked on the check list item
     * @param title notification title
     * @param description  notification description
     * @param status notification category whether it is SUCCESS or ERROR
     * @return event that will be triggered when a life cycle check list item is clicked
     */
    public static Event lifecycleItemCheckedEvent(String appId, String checkedBy, String title, String description, String status) {
        Event event = new Event();
        Category eventStatus;
        if (status.equals("INFO")) {
            eventStatus = Category.INFO;
        } else {
            eventStatus = Category.ERROR;
        }
        Event.EventDispatchType[] eventDispatchTypes =
                new Event.EventDispatchType[]{
                        Event.EventDispatchType.SOCIAL_ACTIVITY
                };
        event.setEventDispatchTypes(eventDispatchTypes);
        event.setEventDispatchTypes(eventDispatchTypes);
        event.setSender(checkedBy);
        event.setCategory(eventStatus);
        event.setTarget(appId);
        event.setMessageBody(description);
        event.setMessageTitle(title);
        return event;
    }

}
