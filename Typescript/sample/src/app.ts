import {$} from "./ijquery";

function greeter(person: string) {
    return "Hi " +person;
}

var hi = greeter('All');
console.log(hi);

$(function() { 
  $("#datepicker").val("initial val");
  $( "#datepicker" ).datepicker();
})

