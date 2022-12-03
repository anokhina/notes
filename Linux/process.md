## Process that locks removable storage

```
lsof | grep '<mountpoint of /dev/sda1>'
```

## Monitoring - htop

<https://linuxtogether.org/htop-command-explanation/>

1 - 8 -- processor cores.

### CPU usage colors

- **Blue** -- low priority processes. (nice > 0)
- **Green** -- processes owned by normal users.
- **Red** -- system processes.
- **Orange** -- IRQ time.
- **Magenta** -- Soft IRQ time.
- **Grey** -- IO Wait time.
- **Cyan** -- Steal time.

### Memory usage colors

- **Green** -- memory pages
- **Blue** -- buffer pages
- **Orange** -- cache pages

### Process states


- R: Running
- T/S: Traced/Stopped --  in stopped (paused) state.
- Z: Zombie or defunct -- completed execution but still has an entry in the process table.
- S: Sleeping

### Load Average -- load of a system for last

- minute
- 5 minutes
- 15 minutes

### Columns

- PID – the Process ID number.
- USER – the process owner.
- PRI – the process priority.
- N – It describes the process priority reset by the user or root.
- VIR – process virtual memory.
- RES – process physical memory.
- SHR – process shared memory.
- S – process state.
- CPU% – CPU percentage.
- MEM% – memory percentage.
- TIME+ – the time since process execution has started.
- Command – the full command execution.

### Shortcut Keys

- h -- show all shortcut keys

