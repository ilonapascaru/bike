
function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}

function addOption(selectbox, text, value) {
    var optn = document.createElement("option");
    optn.text = text;
    optn.value = value;
    selectbox.options.add(optn);
}
function addOption_list() {
    var days = getNumberOfDays();
    for (var i=1; i <= days;++i) {
        addOption(document.formularProgramare.day_list,i,i);
    }
    for (var i=1; i <= 12;++i) {
        addOption(document.formularProgramare.month_list,i,i);
    }
    for (var i=2000; i < 2020;++i) {
        addOption(document.formularProgramare.year_list,i,i);
    }
}











function setbg(color)
{
    document.getElementById("styled").style.background=color
}

function getNumberOfDays(){
  var days = 0;
  var e = document.getElementById("luna");
  var month = e.options[e.selectedIndex].text;

  if(month.localeCompare("4") || month.localeCompare("6") || month.localeCompare("9") || month.localeCompare("11"))
    days=30;
  else if(month.localeCompare("2"))
    days=28;
  else days=31;

  return days;
}