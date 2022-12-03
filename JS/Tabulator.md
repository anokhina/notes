## Tabulator

Select row with checkbox

```
{title: "", width: 10, hozAlign: "center", headerSort:false, 
    formatter: (cell, formatterParams, onRenderer) => { 
        let input = document.createElement("input");
        input.setAttribute("type", "checkbox");
        input.checked = cell.getRow().isSelected();
        input.addEventListener("change", (e) => {
            if (input.checked != cell.getRow().isSelected()) {
                if (input.checked) {
                    cell.getRow().select();
                } else {
                    cell.getRow().deselect();
                }
            }
        });
        return input;
    },
},
```

```
var table = new Tabulator("#example-table", {
    height:"311px",
    columns:[
      {formatter:"rowSelection", titleFormatter:"rowSelection", hozAlign:"center", headerSort:false, cellClick:function(e, cell){
        cell.getRow().toggleSelect();
      }},
      {title:"Name", field:"name", width:200},
      {title:"Progress", field:"progress", width:100, hozAlign:"right", sorter:"number"},
    ],
});
```

## Custom Header

<https://codepen.io/SwatiWakode/pen/wbGpaR>

```css
.header{
  border-radius: 4px;
  margin: 3px;
}
```

```js
var data = [
        {id:1, name:"Oli Bob", age:"12", col:"red", dob:""},
        {id:2, name:"Mary May", age:"1", col:"blue", dob:"14/05/1982"},
        {id:3, name:"Christine Lobowski", age:"42", col:"green", dob:"22/05/1982"},
        {id:4, name:"Brendon Philips", age:"125", col:"orange", dob:"01/08/1980"},
        {id:5, name:"Margret Marmajuke", age:"16", col:"yellow", dob:"31/01/1999"},
    ];
 var columns =  [ //Define Table Columns
            {
              title:"Age",
              field:"age",                                                           titleFormatter:customHeader
            },
            {
              title:"Names",
              field:"name"
            }
 	      ];
 var table = new Tabulator("#example-table", {
        height:205, 
        data:data,
 	      layout:"fitColumns",
        selectable : true,
 	      columns:columns
    });
 $.contextMenu({
       selector: '.tabulator-headers .fa-angle-down',
       autoHide: true,
       trigger: 'left',
       itemClickEvent: "click",
       callback: function(key, options) {
            alert("Column - " + options.$trigger.attr('data-column')+" , Selected - "+ key);
        },
       items: {
                "renameColumn":{
                    name:"Rename Column"
                },
                "changeType": {
                    "name": "Change Type", 
                    "items": {
                        "text": {"name": "Text","icon": "fa-font"},
                        "number": {"name": "Number","icon": "fa-hashtag"},
                        "datetime": {"name": "Date/Time","icon":"fa-calendar"}
                }
            },
         "annotations": {
                    "name": "Annotations", 
                    "items": {
                        "dimension": {"name": "Dimension","icon":""},
                        "join": {"name": "Join","icon":""}
                }
            },
         "moveto": {
                    "name": "Move To", 
                    "items": {
                        "first": {"name": "First"},
                        "before": {"name": "Before"},
                        "after": {"name": "After"},
                        "last": {"name": "Last"}
                }
            },
                "sep": "----- DataTypes -----",
                "exclude":{
                    name:"Exclude"
                },
              }
});  

function customHeader(cell, formatterParams, onRendered){ 
    return '<i class="fa fa-hashtag"></i><input type="text" class="header"><i data-column="'+cell.getValue()+'" class="fa fa-angle-down"></i>';
};
```

## Printing

<http://tabulator.info/docs/4.9/print#formatters>

## CSS

<http://tabulator.info/docs/4.0/style>

## Sort

<http://tabulator.info/docs/4.0/sort>
