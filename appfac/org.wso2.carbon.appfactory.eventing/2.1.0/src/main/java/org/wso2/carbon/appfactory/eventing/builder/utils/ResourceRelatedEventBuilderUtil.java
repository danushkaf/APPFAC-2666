package org.wso2.carbon.appfactory.eventing.builder.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appfactory.eventing.Event;
import org.wso2.carbon.appfactory.eventing.Event.Category;

public class ResourceRelatedEventBuilderUtil {


    private static Log log = LogFactory.getLog(ResourceRelatedEventBuilderUtil.class);



    /**
     *
     * @param appKey application key
     * @param updatedBy  user who updated database privilege
     * @param title notification title
     * @param description notification description
     * @param correlationKey in the format of ApplicationKey-stageName-template-templateName
     * @param category notification category whether it is SUCCESS or ERROR
     * @return event that will be triggered when a database user privilege is modification started
     */
    public static Event resourceUpdateStartEvent(String appKey, String updatedBy, String title, String description, String correlationKey, String category) {

        Category eventStatus;
        if ("INFO".equals(category)) {
            eventStatus = Category.INFO;
        } else {
            eventStatus = Category.ERROR;
        }

        Event event = new Event();
        Event.EventDispatchType[] eventDispatchTypes =
                new Event.EventDispatchType[]{
                        Event.EventDispatchType.SOCIAL_ACTIVITY
                };
        event.setEventDispatchTypes(eventDispatchTypes);
        event.setSender(updatedBy);
        event.setCategory(eventStatus);
        event.setTarget(appKey);
        event.setMessageTitle(title);
        event.setMessageBody(description);
        event.setState(Event.State.START);
        event.setCorrelationKey(correlationKey);
        return event;
    }


    /**
     *
     * @param appKey application key
     * @param updatedBy  user who updated database privilege
     * @param title notification title
     * @param description notification description
     * @param correlationKey in the format of ApplicationKey-stageName-template-templateName
     * @param category notification category whether it is SUCCESS or ERROR
     * @return event that will be triggered when a database user privilege is modified
     */
    public static Event resourceUpdateCompletionEvent(String appKey, String updatedBy, String title, String description, String correlationKey, String category) {

        Category eventStatus;
        if ("INFO".equals(category)) {
            eventStatus = Category.INFO;
        } else {
            eventStatus = Category.ERROR;
        }

        Event event = new Event();
        Event.EventDispatchType[] eventDispatchTypes =
                new Event.EventDispatchType[]{
                        Event.EventDispatchType.SOCIAL_ACTIVITY
                };
        event.setEventDispatchTypes(eventDispatchTypes);
        event.setSender(updatedBy);
        event.setCategory(eventStatus);
        event.setTarget(appKey);
        event.setMessageTitle(title);
        event.setMessageBody(description);
        event.setState(Event.State.COMPLETE);
        event.setCorrelationKey(correlationKey);
        return event;
    }


    /**
     *
     * @param appKey application key
     * @param createdBy  user who created property
     * @param title notification title
     * @param description notification description
     * @param correlationKey in the format of ApplicationKey-stageName-property-propertyname
     * @param category notification category whether it is SUCCESS or ERROR
     * @return event that will be triggered when a property creation started
     */
    public static Event resourceCreationStartedEvent(String appKey, String createdBy, String title, String description, String correlationKey, String category) {

        Category eventStatus;
        if ("INFO".equals(category)) {
            eventStatus = Category.INFO;
        } else {
            eventStatus = Category.ERROR;
        }

        Event event = new Event();
        Event.EventDispatchType[] eventDispatchTypes =
                new Event.EventDispatchType[]{
                        Event.EventDispatchType.SOCIAL_ACTIVITY
                };
        event.setEventDispatchTypes(eventDispatchTypes);
        event.setSender(createdBy);
        event.setCategory(eventStatus);
        event.setTarget(appKey);
        event.setMessageTitle(title);
        event.setMessageBody(description);
        event.setState(Event.State.START);
        event.setCorrelationKey(correlationKey);
        return event;
    }


    /**
     *
     * @param appKey application key
     * @param createdBy  user who created property
     * @param title notification title
     * @param description notification description
     * @param correlationKey in the format of ApplicationKey-stageName-property-propertyname
     * @param category notification category whether it is SUCCESS or ERROR
     * @return event that will be triggered when a property creation started
     */
    public static Event resourceCreationCompletedEvent(String appKey, String createdBy, String title, String description, String correlationKey, String category) {

        Category eventStatus;
        if ("INFO".equals(category)) {
            eventStatus = Category.INFO;
        } else {
            eventStatus = Category.ERROR;
        }

        Event event = new Event();
        Event.EventDispatchType[] eventDispatchTypes =
                new Event.EventDispatchType[]{
                        Event.EventDispatchType.SOCIAL_ACTIVITY
                };
        event.setEventDispatchTypes(eventDispatchTypes);
        event.setSender(createdBy);
        event.setCategory(eventStatus);
        event.setTarget(appKey);
        event.setMessageTitle(title);
        event.setMessageBody(description);
        event.setState(Event.State.COMPLETE);
        event.setCorrelationKey(correlationKey);
        return event;
    }


    /**
     *
     * @param appKey application key
     * @param deletedBy  user who deleted property
     * @param title notification title
     * @param description notification description
     * @param correlationKey in the format of ApplicationKey-stageName-property-propertyname
     * @param category notification category whether it is SUCCESS or ERROR
     * @return event that will be triggered when a property deletion started
     */
    public static Event resourceDeletionStartedEvent(String appKey, String deletedBy, String title, String description, String correlationKey, String category) {

        Category eventStatus;
        if ("INFO".equals(category)) {
            eventStatus = Category.INFO;
        } else {
            eventStatus = Category.ERROR;
        }

        Event event = new Event();
        Event.EventDispatchType[] eventDispatchTypes =
                new Event.EventDispatchType[]{
                        Event.EventDispatchType.SOCIAL_ACTIVITY
                };
        event.setEventDispatchTypes(eventDispatchTypes);
        event.setSender(deletedBy);
        event.setCategory(eventStatus);
        event.setTarget(appKey);
        event.setMessageTitle(title);
        event.setMessageBody(description);
        event.setState(Event.State.START);
        event.setCorrelationKey(correlationKey);
        return event;
    }

    /**
     *
     * @param appKey application key
     * @param deletedBy  user who deleted property
     * @param title notification title
     * @param description notification description
     * @param correlationKey in the format of ApplicationKey-stageName-property-propertyname
     * @param category notification category whether it is SUCCESS or ERROR
     * @return event that will be triggered when a property deletion completed
     */
    public static Event resourceDeletionCompletedEvent(String appKey, String deletedBy, String title, String description, String correlationKey, String category) {

        Category eventStatus;
        if ("INFO".equals(category)) {
            eventStatus = Category.INFO;
        } else {
            eventStatus = Category.ERROR;
        }

        Event event = new Event();
        Event.EventDispatchType[] eventDispatchTypes =
                new Event.EventDispatchType[]{
                        Event.EventDispatchType.SOCIAL_ACTIVITY
                };
        event.setEventDispatchTypes(eventDispatchTypes);
        event.setSender(deletedBy);
        event.setCategory(eventStatus);
        event.setTarget(appKey);
        event.setMessageTitle(title);
        event.setMessageBody(description);
        event.setState(Event.State.COMPLETE);
        event.setCorrelationKey(correlationKey);
        return event;
    }
}
