document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("#convertorForm").onsubmit = function () {
        convert()
        return false
    }
})

function convert() {

    let amount = document.getElementById("amountInput").value
    let firstCurrency = document.getElementById("firstCurrency").value
    let secondCurrency = document.getElementById("secondCurrency").value
    let result = document.getElementById("valueResult")
    result.innerHTML = ""

    fetch('/api/convert',
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                'firstCurrency': firstCurrency,
                'secondCurrency': secondCurrency,
                'value':amount
            }),
        })
        .then(response => {
                if (response.status === 200) {
                    response.json().then(data => {
                        result.insertAdjacentHTML("beforeend",
                            `Result: ${data}`
                        )})
                }
                else {
                    response.json().then(data => {
                        result.insertAdjacentHTML("beforeend",data.message)
                    })
                }
            }
        )
        .catch((error) => {
            console.error('Error:', error);
        });
}

function swapCurrencies(){
    let aux = document.getElementById("firstCurrency").value
    document.getElementById("firstCurrency").value = document.getElementById("secondCurrency").value
    document.getElementById("secondCurrency").value = aux
}