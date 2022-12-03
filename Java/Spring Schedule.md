## Spring Schedule by cron string

Add to configuration **backupScheduler**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans  xmlns           =   "http://www.springframework.org/schema/beans"
       
        xmlns:xsi       =   "http://www.w3.org/2001/XMLSchema-instance"
        xmlns:task      =   "http://www.springframework.org/schema/task"
       
        xsi:schemaLocation="
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-4.3.xsd

http://www.springframework.org/schema/task
http://www.springframework.org/schema/task/spring-task-4.3.xsd
">
	<task:scheduler id="backupScheduler" pool-size="100"/>
</beans>
```

Implement service

```java
@Service
public class ScheduleClientBackupService {

    @Autowired
    @Qualifier ("backupScheduler")
    private TaskScheduler scheduler;

    private final Map<Long, ScheduledFuture<?>> jobsMap = new HashMap<> ();

    private void addTaskToScheduler (final Long id, final String cron, final Runnable task) throws Exception {
        final ScheduledFuture<?> scheduledTask = scheduler.schedule (task, new CronTrigger (getCron (cron), TimeZone.getTimeZone (TimeZone.getDefault ().getID ())));

        jobsMap.put (id, scheduledTask);
    }

    private String getCron (final String s) {
        return "0 " + s;
    }

    public void removeTaskFromScheduler (final Long id) {
        final ScheduledFuture<?> scheduledTask = jobsMap.remove (id);
        if (scheduledTask != null) {
            scheduledTask.cancel (true);
        }
    }

    @EventListener ({ ContextRefreshedEvent.class })
    void contextRefreshedEvent () {
        initialize ();
    }

    private void initialize () {
        for (final Long id : getFromSomeService() ) {

            removeTaskFromScheduler (id);
            addTaskToScheduler (id, cron, () -> {});
        }
    }
}
```