<!DOCTYPE html>
<html lang="en">
    
     <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Biker</title>
        <link rel="stylesheet" href="css/style.css">
        <script src="js/functions.js"></script>
      </head>
    
    <body>
        <div class="bg">
        <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
        <div class="sidenav" id="mySidenav">
          <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
      <a href="index.html">Acasa</a>
      <a href="Contact.jsp">Contact</a>
      <a href="About.html">About</a>
        </div>


        <div class="main-contact">
            <div class="container-contact">
                <form class="contact-form" action="/contact" method="post">
                    <span class="contact-form-title">
                        Trimite un mesaj
                    </span>

                    <label class="label-input" for="first-name">Numele dumneavoastra *</label>
                    <div class="input dreapta-input-plh">
                        <input id="first-name" class="input-plh" type="text" name="first-name" placeholder="Prenume">
                        <span class="focus-input-plh"></span>
                    </div>
                    <div class="input stanga-input-plh">
                        <input class="input-plh" type="text" name="last-name" placeholder="Nume">
                        <span class="focus-input-plh"></span>
                    </div>

                    <label class="label-input" for="email-form">Adresa de email *</label>
                    <div class="input">
                        <input id="email-form" class="input-plh" type="text" name="email" placeholder="email@email.com">
                        <span class="focus-input-plh"></span>
                    </div>

                    <label class="label-input" for="phone">Numarul de telefon</label>
                    <div class="input">
                        <input id="phone" class="input-plh" type="text" name="phone" placeholder="07xx.xxx.xxx">
                        <span class="focus-input-plh"></span>
                    </div>

                    <label class="label-input" for="message">Mesaj *</label>
                    <div class="input">
                        <textarea id="message" class="input-plh" name="message" placeholder="Adaugati un mesaj"></textarea>
                        <span class="focus-input-plh"></span>
                    </div>

                    <div class="form-button">
                        <button class="button">
                            Trimite
                        </button>
                    </div>
                </form>

            </div>
        </div>
  </div>

    </body>
</html>
