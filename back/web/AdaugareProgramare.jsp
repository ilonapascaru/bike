<!DOCTYPE html>
<html lang="ro">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Biker</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/functions.js"></script>
    
  </head>

  <body >
    <div class="bg">
    <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
    <div class="sidenav" id="mySidenav">
      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
      <a href="User.jsp">Programari</a>
      <a href="Contact.jsp">Contact</a>
      <a href="About.html">About</a>
      <a href="index.html">Logout</a>

    </div>
    
    <div class="adaugaProgramare">
    <h2>Adauga programare</h2>
    
        <div class="formular-adaugare">
          <form class="formular" action="\adaugaProgramari" method="post" name="formularProgramare" enctype="multipart/form-data">
            <label for="model" style="color:black">Model:</label>
            <input type="text" id="model" name="model"/><br>
            <label for="descriere" style="color:black">Descriere:</label>
            <input type="text" id="descriere" name="descriere" ><br>
            <label style="color:black">Data:</label>
            <select id="daydropdown" name="ziua"></select>
            <select id="monthdropdown" name="luna"></select>
            <select id="yeardropdown" name="an"></select>
            <label for="ora" style="color:black">Ora:</label>
            <select id="ora" name="ora">
              <option value="12">12:00</option>
              <option value="14">14:00</option>
              <option value="16">16:00</option>
              <option value="18">18:00</option>
        </select><br>
             <label for="fisier" style="color:black">Adauga fotografie descriptiva</label><br>
            <input type="file" style="color:black" class="buton-import" id="fisier" name="fisier"/>
            <a href="#" onclick="showFileName()">Show Name</a>
            <br>
            <input type="submit" class="buton-cerere" value="Trimite cerere"/>


          </form>
        </div>
</div>
</div>
</div>

    <script type="text/javascript">
        var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'];

        function modifyLuna(){
            var lunaC = document.getElementById("monthdropdown");
            document.getElementById("luna").innerHTML= lunaC;
            document.getElementById("luna").value = lunaC;
        }

        function modifyAn(){
            var anC = document.getElementById("yearhdropdown");
            document.getElementById("an").innerHTML = anC;
            document.getElementById("an").value =anC;
        }

        function daysInMonth(month, year) {
            return new Date(year, month, 0).getDate();
        }

        function populateDates(){
            var today = new Date(),
                day = today.getUTCDate(),
                month = today.getUTCMonth(),
                year = today.getUTCFullYear(),
                daysInCurrMonth = daysInMonth(month, year);

            // Year
            for(var i = 0; i < 21; i++){
                var opt = document.createElement('option');
                opt.value = i + year;
                opt.text = i + year;
                yeardropdown.appendChild(opt);
            }

            // Month
            for(var i = 0; i < 12; i++){
                var opt = document.createElement('option');
                opt.value = i + 1;
                opt.text = months[i];
                monthdropdown.appendChild(opt);
            }

            // Day
            for(var i = 0; i < daysInCurrMonth; i++){
                var opt = document.createElement('option');
                opt.value = i + 1;
                opt.text = i + 1;
                daydropdown.appendChild(opt);
            }
        }

        var daydropdown = document.getElementById("daydropdown"),
            monthdropdown = document.getElementById("monthdropdown"),
            yeardropdown = document.getElementById("yeardropdown");

        // Change handler for months
        monthdropdown.onchange = function(){
            var newMonth = months.indexOf(monthdropdown.value) + 1,
                newYear = yeardropdown.value;

            daysInCurrMonth = daysInMonth(newMonth, newYear);

            daydropdown.innerHTML = "";
            for(var i = 0; i < daysInCurrMonth; i++){
                var opt = document.createElement('option');
                opt.value = i + 1;
                opt.text = i + 1;
                daydropdown.appendChild(opt);
            }
        }

        function showFileName() {
            var fil = document.getElementById("fisier");
            alert(fil.value);
        }

        populateDates()
    </script>
  </body>
</html>
