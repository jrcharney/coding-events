# Coding Events

> Note: This really isn't a list of coding events. It is more of an exercise using Thymeleaf.
> It could have practical applications though.

Turns out I didn't commit after each subchapter so I can't do the exercise here. So there is no `my-exercise-solution` branch.

Starting in Chapter 12, a new branch `create-model` is used for this project with Models.

Also tried to fix a couple of things that weren't done in chapter 13 and 14.

If you are reading this, welcome to many-to-one!

In chapter 18, as much as it irks me to downgrade software, changes need to be made to `build.gradle` and `gradle/gradle-wrapper.properties`.

In `build.gradle`, `org.springframework.boot` needs to be downgraded to version 2.2.7.

```
plugins {
   id 'org.springframework.boot' version '2.2.7.RELEASE'
   id 'io.spring.dependency-management' version '1.0.9.RELEASE'
   id 'java'
}

...
```

In `gradle/gradle-wrapper.properties`, the `distributionURL` needs to be set to 6.3-all.

```
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-6.3-all.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

Those changes mean another 145MB of software to download. So be sure to run a `gradle clean build` to get rid of unused versions.

Anytime you need to change your SQL tables, it will be easier just to DROP them than TRUNCATE them. Do it in this order.

```sql
DROP TABLE IF EXISTS coding_events.hibernate_sequence;
DROP TABLE IF EXISTS coding_events.event;
DROP TABLE IF EXISTS coding_events.event_category;
```

That should fix things. I'm not happy with the downgrade, but thanks to Bobby Sanders for the fix.

> PSST: Check out the SQL files in the assets folder!