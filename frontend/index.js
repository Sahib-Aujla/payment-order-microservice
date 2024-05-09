document.addEventListener("DOMContentLoaded", function () {
  fetchOrders();
  fetchPayments();
});

const URL_API = "http://localhost:8080/order";

function fetchOrders() {
  fetch(URL_API)
    .then((response) => response.json())
    .then((data) => {
      const table = document
        .getElementById("ordersTable")
        .getElementsByTagName("tbody")[0];
      table.innerHTML = "";
      data.forEach((order) => {
        let row = table.insertRow();
        row.insertCell(0).innerText = order.id;
        row.insertCell(1).innerText = order.customerName;
        row.insertCell(2).innerText = order.productName;
        row.insertCell(3).innerText = new Date(
          order.orderDate
        ).toLocaleString();
        row.insertCell(4).innerText = order.paymentStatus;
        row.insertCell(5).innerText = order?.totalPrice;
        let actionsCell = row.insertCell(6);
        actionsCell.appendChild(
          createButton("Delete", () => deleteOrder(order.id))
        );
      });
    });
}

const URL_PAY = "http://localhost:8081/payment/all";
function fetchPayments() {
  fetch(URL_PAY)
    .then((response) => response.json())
    .then((data) => {
      const table = document
        .getElementById("paymentsTable")
        .getElementsByTagName("tbody")[0];
      table.innerHTML = "";
      data.forEach((payment) => {
        let row = table.insertRow();
        row.insertCell(0).innerText = payment?.paymentId;
        row.insertCell(1).innerText = payment?.orderId;
        row.insertCell(2).innerText = payment?.paymentAmount;
        row.insertCell(3).innerText = new Date(
          payment?.paymentDate
        ).toLocaleString();
        row.insertCell(4).innerText = payment?.paymentStatus;
      });
    });
}

function createButton(text, func) {
  let btn = document.createElement("button");
  btn.textContent = text;
  btn.onclick = func;
  return btn;
}

function submitOrder() {
  console.log("here");
  let url = URL_API + "/placeOrder";
  let method = "POST";
  
  const order = {
    customerName: document.getElementById("customerName").value,
    productName: document.getElementById("productName").value,
    totalPrice: parseFloat(document.getElementById("totalPrice").value),
    quantity: parseInt(document.getElementById("quantity").value),
  };
  console.log({ order });
  fetch(url, {
    method: method,
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(order),
  })
    .then((response) => response.json())
    .then(() => {
      fetchOrders();
      document.getElementById("orderForm").reset();
      document.getElementById("orderId").value = "";
    });
}

function deleteOrder(orderId) {
  fetch(URL_API + `/delete/${orderId}`, { method: "DELETE" }).then(() =>
    fetchOrders()
  );
}
