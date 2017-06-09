# Iterable Android SDK

`iterable-android-sdk` is a java implementation of an android client for Iterable, for api version 15 and higher.

# Setting up a push integration in Iterable

Before you even start with the SDK, you will need to 

1. Set your application up to receive push notifications, and 
2. Set up a push integration in Iterable. This allows Iterable to communicate on your behalf with Google's Push Notification Service.

You will also need to generate an SSL certificate and private key for use with the push service. See the links at the end of this section for more information on how to do that.

Once you have your APNS certificates set up, go to `Integrations -> Mobile Push` in Iterable. When creating an integration, you will need to pick a name and a platform. The name is entirely up to you; it will be the `applicationName` when you use `registerDeviceToken` in our SDK. The platform will be `GCM`. Add the GCM api server key.

![Creating an integration in Iterable](https://support.iterable.com/hc/en-us/article_attachments/211841066/2016-12-08_1442.png)

For more information, see

* [Configuring Push Notifications](http://docs.aws.amazon.com/sns/latest/dg/mobile-push-gcm.html)

Congratulations, you've configured your mobile application to receive push notifications! Now, let's set up the Iterable SDK...

# Automatic Installation

See BinTray for the latest version of the Iterable Android SDK on [Bintray](https://bintray.com/davidtruong/maven/Iterable-SDK)

#### InApp Notifications
To display the user's InApp notifications call `spawnInAppNotification` with a defined `IterableActionHandler` callback handler. When a user clicks a button on the notification, the defined handler is called and passed the action name defined in the InApp template.

InApp opens and button clicks are automatically tracked when the notification is called via `spawnInAppNotification`.

# Additional Information

See our [setup guide](http://support.iterable.com/hc/en-us/articles/204780589-Push-Notification-Setup-iOS-and-Android-) for more information.

Also see our [push notification setup FAQs](http://support.iterable.com/hc/en-us/articles/206791196-Push-Notification-Setup-FAQ-s).

# Deeplinking

See our [Deeplinking Setup Guide] (https://support.iterable.com/hc/en-us/articles/211676923)

From your application's [onCreate] (https://developer.android.com/reference/android/app/Activity.html#onCreate(android.os.Bundle)) call `getAndTrackDeeplink` along with a callback to handle the destination deeplink url.

```java
protected void onCreate(Bundle savedInstanceState) {
	String dataUri = this.getIntent().getDataString();
	IterableHelper.IterableActionHandler clickCallback = 
		new IterableHelper.IterableActionHandler(){
			@Override
			public void execute(String result) {
			    Log.d("HandleDeeplink", "Redirected to: "+ result);
			    //handle deeplink here
			}
		};
	
	IterableApi.getAndTrackDeeplink(dataUri, clickCallback);
}
```

# Firebase Messaging
At this time there is no requirement to upgrade to FCM since Google will continue to support current versions of GCM android.

If you want to use using Firebase Cloud Messaging (FCM) instead of Google Cloud Messaging (GCM) pass in `IterableConstants. MESSAGING_PLATFORM_FIREBASE` as the pushServicePlatform.

```java
public void registerForPush(String iterableAppId, String projectNumber, String pushServicePlatform) {
```

**Note**: If you are upgrading to FCM, do not downgrade back to GCM as this will cause devices to be registered for notifications twice and users will get duplicate notifications.

# License

The MIT License

See [LICENSE](https://github.com/Iterable/iterable-android-sdk/blob/master/LICENSE)

## Want to Contribute?

This library is open source, and we will look at pull requests!

See [CONTRIBUTING](CONTRIBUTING.md) for more information.