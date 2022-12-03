## Keycodes

Show codes for alt key

```
xmodmap -pk | grep -i alt
```

### Remap

<https://guillermo.at/ubuntu-key-remap>

```
cd /usr/share/X11/xkb/symbols
grep -R "XF86HomePage" .
```

Shows

```
./inet:    key <I180>   {      [ XF86HomePage          ] 
```

Edit file inet

```
key <I180>   {      [ Insert          ]       };
```


## Mouse buttons settings

<https://d1mon.com/n/1687>

Determine mouse buttons

```
$ xev | grep button
    state 0x0, button 1, same_screen YES
    state 0x100, button 1, same_screen YES
    state 0x0, button 3, same_screen YES
    state 0x400, button 3, same_screen YES
    state 0x0, button 9, same_screen YES
    state 0x0, button 9, same_screen YES
    state 0x0, button 8, same_screen YES
    state 0x0, button 8, same_screen YES
```

or any button

```
xev | grep keycode
```

Install xbindkeys and xautomation

```
$ sudo apt install xbindkeys
$ sudo apt install xautomation
```

Create settings file for xbindkeys

```
$ xbindkeys --defaults > ~/.xbindkeysrc
```

View help for xautomation

```
$ xte -h
```

Edit ```~/.xbindkeysrc```. Find ```# Examples of commands:``` and comment

```
#"xbindkeys_show" 
#  control+shift + q
```

and insert

```
"xte 'keydown Control_L' 'key Insert' 'keyup Control_L'"
  b:8

"xte 'keydown Shift_L' 'key Insert' 'keyup Shift_L'"
  b:9
```

or

```
"xte 'keydown Control_L' 'key c' 'keyup Control_L'"
  b:8

"xte 'keydown Control_L' 'key v' 'keyup Control_L'"
  b:9
```

Test settings

```
$ xbindkeys -n -v
```

Place xbindkeys in autorun. For KDE  
System settings -> Workspace -> Startup and Shutdown -> Autostart

## Switch off mouse

<https://habr.com/ru/sandbox/53791/>
