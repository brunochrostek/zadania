<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>Weryfikacja Danych PHP</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
        }
        h2 { text-align: center; color: #333; }
        input, textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #5c67f2;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }
        button:hover { background-color: #4a54e1; }
        .result {
            margin-top: 20px;
            padding: 15px;
            border-left: 5px solid #5c67f2;
            background: #eef0ff;
            width: 100%;
            max-width: 400px;
        }
        .warning { color: #d9534f; font-weight: bold; }
    </style>
</head>
<body>

<div class="container">
    <h2>Formularz Kontaktowy</h2>
    <form method="POST">
        <input type="text" name="imie" placeholder="Twoje imię" required>
        <input type="email" name="email" placeholder="Adres e-mail" required>
        <textarea name="wiadomosc" rows="4" placeholder="Twoja wiadomość" required></textarea>
        <button type="submit">Wyślij dane</button>
    </form>
</div>
 <?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $imie = htmlspecialchars($_POST['imie']);
    $email = htmlspecialchars($_POST['email']);
    $wiadomosc = htmlspecialchars($_POST['wiadomosc']);

    echo "<div class='result'>";

    function sprawdz_domene($email) {
        $czesci = explode('@', $email);
        return end($czesci);
    }
    
    $domena = sprawdz_domene($email);
    echo "<p><strong>Domena e-mail:</strong> $domena</p>";

    $pattern = "/(dupa|dupek)/i";
    if (preg_match($pattern, $wiadomosc)) {
        echo "<p class='warning'>⚠️ Ostrzeżenie: Twoja wiadomość zawiera niedozwolone słownictwo!</p>";
    }

    $dane_tablica = [$imie, $email, $wiadomosc];
    $raport = implode(' | ', $dane_tablica);

    echo "<h3>Raport końcowy:</h3>";
    echo "<code>$raport</code>";
    echo "</div>";
}
?>
</body>
</html>