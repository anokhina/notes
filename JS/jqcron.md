## jqcron jquery plugin

<https://jqcron.arnapou.net/demo/?page=demo_6>

```
$(function(){
    var cron =
        $('.example5')
        .jqCron()
        .jqCronGetInstance();
     
    $('.a5-enable').click(function(e){
        cron.enable();
        e.preventDefault();
    });
     
    $('.a5-disable').click(function(e){
        cron.disable();
        e.preventDefault();
    });
     
    $('.a5-toggle').click(function(e){
        if(cron.isDisabled())
            cron.enable();
        else
            cron.disable();
        e.preventDefault();
    });
});
```

```
<div class="example5"></div>
<p style="margin-top:1em">
    <a href="#" class="a5-enable">Enable</a> 
    <a href="#" class="a5-disable">Disable</a> 
    <a href="#" class="a5-toggle">Toggle</a> 
</p>
```