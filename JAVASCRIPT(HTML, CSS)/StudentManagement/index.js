////constructor function va ke thua bang prototype
//quan ly sinh vien
function Student(name, birthday) {
  this.name = name;
  this.birthday = birthday;
  this.id = new Date().toISOString();
}

//store:localstore
function Store() {}
//getStudent: lay ra danh sacch sinh vien tu sudents tu LocalStorage
Store.prototype.getStudents = function () {
  return JSON.parse(localStorage.getItem("students")) || [];
};
let a = new Store();

//add: them student moii vao local storage
Store.prototype.add = function (newStudent) {
  //lay danh sach tu LocalStorage xuong
  const students = this.getStudents();
  //nhet newStudent vao students
  students.push(newStudent);
  //cap nhat gia tri
  localStorage.setItem("students", JSON.stringify(students));
};
//getStudent: ham nhan vao id tim sinh vien so huu id do
Store.prototype.getStudent = function (id) {
  //lay danh sach sinh vien va nhan vao id can tim
  const students = this.getStudents();
  return students.find((student) => student.id == id);
};
Store.prototype.remove = function (id) {
  //lay danh sach sinh vien va nhan vao id can tim
  const students = this.getStudents();
  const indexRemove = students.findIndex((student) => student.id == id);
  students.splice(indexRemove, 1);
  localStorage.setItem("students", JSON.stringify(students));
};

//renderUi
function renderUI() {}
//add: them student moii vao giao dien
renderUI.prototype.add = function (newStudent) {
  //lay danh sach students
  const students = new Store().getStudents();
  const newTr = document.createElement("tr");
  const { name, birthday, id } = newStudent; //destructoring
  newTr.innerHTML = `
  <td>${students.length}</td>
  <td>${name}</td>
  <td>${birthday}</td>
  <td>
    <button class="btn btn-danger bbtn-sm btn-remove" data-id=${id}>
      Delete
    </button>
  </td>`;
  document.querySelector("tbody").appendChild(newTr);
  document.querySelector("#name").value = "";
  document.querySelector("#birthday").value = "";
};

//alert:nhan vao msg() va type
renderUI.prototype.alert = function (msg) {
  const divalert = document.createElement("div");
  //them class
  divalert.className = `alert alert-success`;
  divalert.innerHTML = msg;
  document.querySelector("#notification").appendChild(divalert);
  setTimeout(() => {
    divalert.remove();
  }, 2000);
};

renderUI.prototype.renderAll = function () {
  //1: lay danh sach students tu localstorage
  const store = new Store();
  const students = store.getStudents();
  //: Duyet tung thang va hien thi len UI
  const htmlContent = students.reduce(
    (total, currentStudent, indexStudentcurret) => {
      const { name, birthday, id } = currentStudent;
      return (
        total +
        `
    <tr>
    <td>${indexStudentcurret + 1}</td>
    <td>${name}</td>
    <td>${birthday}</td>
    <td>
      <button class="btn btn-danger bbtn-sm btn-remove" data-id="${id}">
        Delete
      </button>
    </td>
  </tr>
    `
      );
    },
    ""
  );
  document.querySelector("tbody").innerHTML = htmlContent;
};

//-----------------------------------------------
//submit form them student
document.querySelector("form").addEventListener("submit", (event) => {
  event.preventDefault();
  const name = document.querySelector("#name").value;
  const birthday = document.querySelector("#birthday").value;
  const newStudent = new Student(name, birthday);
  const store = new Store();
  const Ui = new renderUI();
  store.add(newStudent);
  Ui.add(newStudent);
  Ui.alert(`Insert new student:${name} success`);
});

// su kien khi trang web vua load xong:dung 1 ham ten la render all
//renderAll: lay danh sach sinh vien tu local store va render ra UI
document.addEventListener("DOMContentLoaded", (event) => {
  const UI = new renderUI();
  UI.renderAll();
});

document.querySelector("tbody").addEventListener("click", (event) => {
  if (event.target.classList.contains("btn-remove")) {
    //lay data-id
    const idRemove = event.target.dataset.id;
    let store = new Store();
    let UI = new renderUI();
    let student = store.getStudent(idRemove);
    let isConirmed = confirm(`Are you sure that delete ${student.name}`);
    //xin confirm
    if (isConirmed) {
      store.remove(idRemove);
      UI.renderAll();
      UI.alert(`You deleted ${student.name}`);
    }
  }
});
