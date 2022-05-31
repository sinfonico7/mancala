
const startGame = () => {
    const players = []
    const playerA = {
        playerName : document.getElementById('player-a-name').value
    }
    const playerB = {
        playerName : document.getElementById('player-b-name').value
    }
    if(playerA.playerName === '' || playerB.playerName === ''){
        alert('You have to set the names of the players');
        return;
    }
    players.push(playerA,playerB);
   
    $('#game-table').show();
    $("#players-data").hide();
    fetch("http://localhost:8080/api/v1/games/mancala/startGame", {
        method: "POST",
        headers: {'Content-Type': 'application/json','Access-Control-Allow-Origin':'http://localhost:8080'}, 
        body: JSON.stringify(players)
      }).then(res => res.json())
      .then(data => {
         //console.log(data)
         paintTable(data);
         
         $("#player-p-a-name").text(playerA.playerName);
        $("#player-p-b-name").text(playerB.playerName);

      }).catch(err => {
          console.error('Error: ', err);
      });
    
};

const playerBMakeMove = () => {
    const movementReq = {
        playerName : $("#player-p-b-name").text(),
        selectedPotIndex : $("input[type='radio'][name='b_area']:checked").val()
    }
    makeMove(movementReq);    
};

const playerAMakeMove = () => {

    const movementReq = {
        playerName : $("#player-p-a-name").text(),
        selectedPotIndex : $("input[type='radio'][name='a_area']:checked").val()
    }
    makeMove(movementReq);
};

const makeMove = (movementReq) => {
    fetch("http://localhost:8080/api/v1/games/mancala/MakeMove", {
        method: "PATCH",
        headers: {'Content-Type': 'application/json'}, 
        body: JSON.stringify(movementReq)
      }).then(res => res.json())
      .then(data => {
         paintTable(data);
      }).catch(err => {
          console.error('Error: ', err);
      });
}

const paintTable = (table) => {
    console.log(table);
    $("#a_button").prop("disabled", !table.players[0].ableToMove);
    $("#b_button").prop("disabled", !table.players[1].ableToMove);
    document.getElementById('player-a-name').innerHTML = table.players[0].playerName;
         document.getElementById('indx-a-0').innerHTML = table.table[0];
         document.getElementById('indx-a-1').innerHTML = table.table[1];
         document.getElementById('indx-a-2').innerHTML = table.table[2];
         document.getElementById('indx-a-3').innerHTML = table.table[3];
         document.getElementById('indx-a-4').innerHTML = table.table[4];
         document.getElementById('indx-a-5').innerHTML = table.table[5];
         document.getElementById('player-a-bp').innerHTML = table.table[6];

         document.getElementById('player-b-name').innerHTML = table.players[1].playerName;
         document.getElementById('indx-b-7').innerHTML = table.table[7];
         document.getElementById('indx-b-8').innerHTML = table.table[8];
         document.getElementById('indx-b-9').innerHTML = table.table[9];
         document.getElementById('indx-b-10').innerHTML = table.table[10];
         document.getElementById('indx-b-11').innerHTML = table.table[11];
         document.getElementById('indx-b-12').innerHTML = table.table[12];
         document.getElementById('player-b-bp').innerHTML = table.table[13];
};