var events = [];
 
function addUserActivity(item, action, appName, appKey, appVersion) {
	var event={};
    event.appName = appName;
    event.appKey = appKey;
    event.appVersion = appVersion;
    event.timestamp = "generate-todo"; //TODO
    event.item = item;
    event.action = action;
    events[events.length] = event;
}

function publishEvents(item, pageUnload, appName, appKey, appVersion) {
    if(pageUnload) {
    	addUserActivity(item, "page-unload", appName, appKey, appVersion);
    } else {
    	addUserActivity(item, "same-page", appName, appKey, appVersion);
    }
    
    var copied = events;
    events = [];

    alert("publishing ******** copied " + copied.length);
    
    jagg.post("../blocks/events/publish/ajax/publish.jag", {
                    action:"userActivity",
                    events:JSON.stringify(copied)
            }, function (result) {
            }, function (jqXHR, textStatus, errorThrown) {
            });
    
    if (!pageUnload) {
        setTimeout(function() {
                publishEvents(item, false);
        } , 15000);
    }
}