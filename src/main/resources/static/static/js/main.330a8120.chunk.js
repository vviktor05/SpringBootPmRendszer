(this["webpackJsonppmrendszer-react"]=this["webpackJsonppmrendszer-react"]||[]).push([[0],{58:function(e,t,a){e.exports=a(93)},64:function(e,t,a){},65:function(e,t,a){},72:function(e,t,a){},90:function(e,t,a){},91:function(e,t,a){},92:function(e,t,a){},93:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),l=a(24),o=a.n(l),s=(a(63),a(64),a(65),a(98)),i=a(99),c=a(100),m=a(6),d=a(7),u=a(57),h=function(e){var t=e.component,a=Object(u.a)(e,["component"]);return r.a.createElement(d.a,Object.assign({},a,{render:function(e){return r.a.createElement(t,e)}}))},p=a(10),E=a(11),b=a(13),g=a(12),f=a(102),v=a(103),j=function(e){Object(b.a)(a,e);var t=Object(g.a)(a);function a(){return Object(p.a)(this,a),t.apply(this,arguments)}return Object(E.a)(a,[{key:"render",value:function(){return r.a.createElement(f.a,{bg:"dark",variant:"dark"},r.a.createElement(m.b,{to:"/",className:"navbar-brand"},"Projektmenedzsment rendszer"),r.a.createElement(f.a.Toggle,{"aria-controls":"basic-navbar-nav"}),r.a.createElement(f.a.Collapse,{id:"basic-navbar-nav"},r.a.createElement(v.a,{className:"mr-auto"},r.a.createElement(m.b,{to:"/projects",className:"nav-link"},"Projektek"),r.a.createElement(m.b,{to:"/customers",className:"nav-link"},"Megrendel\u0151k"))),r.a.createElement(f.a.Collapse,{className:"justify-content-end"},r.a.createElement(f.a.Text,null,localStorage.getItem("user")?r.a.createElement("span",null,"Bel\xe9pve : ",localStorage.getItem("user")," | ",r.a.createElement(m.b,{to:"/login"},"Kijelentkez\xe9s")," "):r.a.createElement(m.b,{to:"/login"},"Bejelentkez\xe9s"))))}}]),a}(r.a.Component),k=a(23),y=a(18),C=(a(72),a(104)),z=a(101),I=a(56),w=a(96),A=function(e){Object(b.a)(a,e);var t=Object(g.a)(a);function a(e){var n;return Object(p.a)(this,a),(n=t.call(this,e)).loginChange=function(e){n.setState(Object(k.a)({},e.target.name,e.target.value))},n.state={email:"",password:""},n.loginChange=n.loginChange.bind(Object(y.a)(n)),n.onSubmit=n.onSubmit.bind(Object(y.a)(n)),n}return Object(E.a)(a,[{key:"onSubmit",value:function(e){e.preventDefault(),this.checkDetails()&&this.props.history.push("/projects")}},{key:"checkDetails",value:function(){return!(this.state.email.length<5)||(alert("Az email nem lehet r\xf6videbb 5 karaktern\xe9l!"),!1)}},{key:"render",value:function(){var e=this.state,t=e.email,a=e.password;return r.a.createElement(C.a,{id:"loginFormContainer",className:"border border-dark bg-dark text-white"},r.a.createElement(C.a.Header,null," Bejelentkez\xe9s "),r.a.createElement(z.a,{onSubmit:this.onSubmit,id:"projektForm"},r.a.createElement(C.a.Body,null,r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formEmail"},r.a.createElement(z.a.Label,null,"Email"),r.a.createElement(z.a.Control,{required:!0,type:"email",name:"email",value:t,autoComplete:"off",onChange:this.loginChange,className:"bg-dark text-white",placeholder:"Add meg az email c\xedmed"}))),r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formPassword"},r.a.createElement(z.a.Label,null,"Jelsz\xf3"),r.a.createElement(z.a.Control,{required:!0,type:"password",name:"password",value:a,autoComplete:"off",onChange:this.loginChange,className:"bg-dark text-white",placeholder:"Add meg a jelszavad"})))),r.a.createElement(C.a.Footer,null,r.a.createElement(w.a,{variant:"primary",type:"submit"},"Bejelentkez\xe9s"))))}}]),a}(n.Component),S=a(97),L=a(9),N=a.n(L),x=function(e){return"".concat("http://localhost:8080","/").concat(e)},O=function(e){Object(b.a)(a,e);var t=Object(g.a)(a);function a(e){var n;return Object(p.a)(this,a),(n=t.call(this,e)).deleteProject=function(e){window.confirm("Biztosan t\xf6rli a kiv\xe1lasztott projektet?")&&N.a.delete(x("api/project_manager/projects/"+e)).then((function(t){200===t.status&&(n.setState({projects:n.state.projects.filter((function(t){return t.id!==e}))}),alert("A projekt t\xf6r\xf6lve!"))}))},n.state={projects:[]},n}return Object(E.a)(a,[{key:"componentDidMount",value:function(){var e=this;N.a.get(x("api/project_manager/projects")).then((function(e){return e.data})).then((function(t){return e.setState({projects:t})}))}},{key:"render",value:function(){var e=this;return r.a.createElement(C.a,{className:"border border-dark bg-dark text-white"},r.a.createElement(C.a.Header,null,"Projektek"),r.a.createElement(C.a.Body,null,r.a.createElement(m.b,{to:"/projects/add"},r.a.createElement(w.a,{variant:"success"},"Projekt hozz\xe1ad\xe1sa")),r.a.createElement(S.a,{className:"marginTop",bordered:!0,hover:!0,striped:!0,variant:"dark"},r.a.createElement("thead",null,r.a.createElement("tr",null,r.a.createElement("th",null,"N\xe9v"),r.a.createElement("th",null,"Megrendel\u0151"),r.a.createElement("th",null,"Megrendel\xe9s d\xe1tuma"),r.a.createElement("th",null,"Hat\xe1rid\u0151 d\xe1tuma"),r.a.createElement("th",null,"Fejleszt\xe9si ter\xfclet"),r.a.createElement("th",null,"Projekt \xe1llapot"),r.a.createElement("th",null,"Priorit\xe1s"),r.a.createElement("th",null,"Projektvezet\u0151"),r.a.createElement("th",null,"St\xe1tusz"),r.a.createElement("th",null))),r.a.createElement("tbody",null,0===this.state.projects.length?r.a.createElement("tr",{align:"center"},r.a.createElement("td",{colSpan:"10"},"Nincs el\xe9rhet\u0151 projekt.")):this.state.projects.sort((function(e,t){return e.id-t.id})).map((function(t){return r.a.createElement("tr",{key:t.id},r.a.createElement("td",null,t.name),r.a.createElement("td",null,t.customer.name),r.a.createElement("td",null,t.orderDate),r.a.createElement("td",null,t.deadline),r.a.createElement("td",null,t.developmentArea.name),r.a.createElement("td",null,t.projectStatus.name),r.a.createElement("td",null,t.priority.name),r.a.createElement("td",null,t.projectLeader.name),r.a.createElement("td",null,t.status.name),r.a.createElement("td",null,r.a.createElement(m.b,{to:"projects/details/"+t.id,className:"mr-2 btn btn-sm btn-primary disable"},"Inform\xe1ci\xf3"),r.a.createElement(m.b,{to:"projects/edit/"+t.id,className:"mr-2 btn btn-sm btn-primary"},"M\xf3dos\xedt"),r.a.createElement(w.a,{variant:"danger",onClick:e.deleteProject.bind(e,t.id),size:"sm"},"T\xf6r\xf6l")))}))))))}}]),a}(n.Component),P=(a(90),function(e){Object(b.a)(a,e);var t=Object(g.a)(a);function a(e){var n;return Object(p.a)(this,a),(n=t.call(this,e)).initialState={id:"",name:"",customerId:1,orderDate:"",deadline:"",developmentAreaId:1,projectStatusId:1,priorityId:1,statusId:1,description:""},n.projectChange=function(e){n.setState(Object(k.a)({},e.target.name,e.target.value))},n.projectList=function(){return n.props.history.push("/projects")},n.resetProject=function(){n.setState((function(){return n.initialState}))},n.state={id:"",name:"",customerId:1,orderDate:"",deadline:"",developmentAreaId:1,projectStatusId:1,priorityId:1,statusId:1,description:"",customerList:[],developmentAreaList:[],projectStatusList:[],priorityList:[],statusList:[]},n.projectChange=n.projectChange.bind(Object(y.a)(n)),n.onSubmit=n.onSubmit.bind(Object(y.a)(n)),n}return Object(E.a)(a,[{key:"componentDidMount",value:function(){this.findAllLists();var e=+this.props.match.params.id;e&&this.findProjectById(e)}},{key:"findAllLists",value:function(){var e=this;N.a.get(x("api/project_manager/customers")).then((function(e){return e.data})).then((function(t){return e.setState({customerList:t})})),N.a.get(x("api/project_manager/development_areas")).then((function(e){return e.data})).then((function(t){return e.setState({developmentAreaList:t})})),N.a.get(x("api/project_manager/project_statuses")).then((function(e){return e.data})).then((function(t){return e.setState({projectStatusList:t})})),N.a.get(x("api/project_manager/priorities")).then((function(e){return e.data})).then((function(t){return e.setState({priorityList:t})})),N.a.get(x("api/project_manager/statuses")).then((function(e){return e.data})).then((function(t){return e.setState({statusList:t})}))}},{key:"findProjectById",value:function(e){var t=this;N.a.get(x("api/project_manager/projects/id/"+e)).then((function(e){200===e.status&&t.setState({id:e.data.id,name:e.data.name,customerId:t.findProjectIndexInArray(t.state.customerList,e.data.customer.id),orderDate:e.data.orderDate,deadline:e.data.deadline,developmentAreaId:t.findProjectIndexInArray(t.state.developmentAreaList,e.data.developmentArea.id),projectStatusId:t.findProjectIndexInArray(t.state.projectStatusList,e.data.projectStatus.id),priorityId:t.findProjectIndexInArray(t.state.priorityList,e.data.priority.id),statusId:t.findProjectIndexInArray(t.state.statusList,e.data.status.id),description:e.data.description})})).catch((function(e){console.error("Hiba - "+e)}))}},{key:"findProjectIndexInArray",value:function(e,t){for(var a=0;a<e.length;a++)if(e[a].id===t)return a+1;return 0}},{key:"onSubmit",value:function(e){e.preventDefault();if(this.checkDetails()){var t={name:this.state.name,customer:this.state.customerList[this.state.customerId-1],orderDate:this.state.orderDate,deadline:this.state.deadline,developmentArea:this.state.developmentAreaList[this.state.developmentAreaId-1],projectStatus:this.state.projectStatusList[this.state.projectStatusId-1],priority:this.state.priorityList[this.state.priorityId-1],projectLeader:{id:1,name:"Horv\xe1th Krisztina",email:"manager@gmail.com",job:{id:1,name:"Projektvezet\u0151"},developmentArea:{id:1,name:"Asztali alkalmaz\xe1s"},skill:{id:4,name:"Senior"},startDate:"2020-01-31",phoneNumber:"06701122345",lastLoginDate:"2020-03-07 18:28:22"},status:this.state.statusList[this.state.statusId-1],description:this.state.description};this.state.id?this.editProject(t):this.addProject(t)}}},{key:"addProject",value:function(e){var t=this;N.a.post(x("api/project_manager/projects"),e).then((function(e){200===e.status&&(t.resetProject(),alert("A project elmentve!"),t.projectList())}))}},{key:"editProject",value:function(e){var t=this;N.a.put(x("api/project_manager/projects/"+this.state.id),e).then((function(e){200===e.status&&(t.resetProject(),alert("A project elmentve!"),t.projectList())}))}},{key:"checkDetails",value:function(){if(this.state.name.length>=5){if(new Date(this.state.orderDate)<new Date(this.state.deadline))return!0;alert("A hat\xe1rid\u0151nek k\xe9s\xf6bbi id\u0151pontnak kell lennie, mint a megrendel\xe9s d\xe1tum\xe1nak!")}else alert("A projekt neve nem lehet r\xf6videbb 5 karaktern\xe9l!");return!1}},{key:"render",value:function(){var e=this.state,t=e.name,a=e.customerId,n=e.orderDate,l=e.deadline,o=e.developmentAreaId,s=e.projectStatusId,i=e.priorityId,c=e.statusId,d=e.description;return r.a.createElement(C.a,{id:"projectFormContainer",className:"border border-dark bg-dark text-white"},r.a.createElement(C.a.Header,null," ",this.state.id?"Projekt m\xf3dos\xedt\xe1sa":"Projekt hozz\xe1ad\xe1sa"," "),r.a.createElement(z.a,{onReset:this.resetProject,onSubmit:this.onSubmit,id:"projektForm"},r.a.createElement(C.a.Body,null,r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formName"},r.a.createElement(z.a.Label,null,"Projekt neve"),r.a.createElement(z.a.Control,{required:!0,type:"text",name:"name",value:t,autoComplete:"off",onChange:this.projectChange,className:"bg-dark text-white",placeholder:"Add meg a projekt nev\xe9t"})),r.a.createElement(z.a.Group,{as:I.a,controlId:"formCustomer"},r.a.createElement(z.a.Label,null,"Megrendel\u0151"),r.a.createElement(z.a.Control,{className:"bg-dark text-white",as:"select",name:"customerId",value:a,onChange:this.projectChange},this.state.customerList.map((function(e){return r.a.createElement("option",{key:e.id,value:e.id},e.name)}))))),r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formOrderDate"},r.a.createElement(z.a.Label,null,"Megrendel\xe9s d\xe1tuma"),r.a.createElement(z.a.Control,{required:!0,value:n,onChange:this.projectChange,type:"date",className:"bg-dark text-white",name:"orderDate"})),r.a.createElement(z.a.Group,{as:I.a,controlId:"formDeadline"},r.a.createElement(z.a.Label,null,"Hat\xe1rid\u0151 d\xe1tuma"),r.a.createElement(z.a.Control,{required:!0,value:l,onChange:this.projectChange,type:"date",className:"bg-dark text-white",name:"deadline"}))),r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formDevelopmentArea"},r.a.createElement(z.a.Label,null,"Fejleszt\xe9si ter\xfclet"),r.a.createElement(z.a.Control,{className:"bg-dark text-white",as:"select",name:"developmentAreaId",value:o,onChange:this.projectChange},this.state.developmentAreaList.map((function(e){return r.a.createElement("option",{key:e.id,value:e.id},e.name)})))),r.a.createElement(z.a.Group,{as:I.a,controlId:"formProjectStatus"},r.a.createElement(z.a.Label,null,"Projekt \xe1llapot"),r.a.createElement(z.a.Control,{className:"bg-dark text-white",as:"select",name:"projectStatusId",value:s,onChange:this.projectChange},this.state.projectStatusList.map((function(e){return r.a.createElement("option",{key:e.id,value:e.id},e.name)}))))),r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formPriority"},r.a.createElement(z.a.Label,null,"Priorit\xe1s"),r.a.createElement(z.a.Control,{className:"bg-dark text-white",as:"select",name:"priorityId",value:i,onChange:this.projectChange},this.state.priorityList.map((function(e){return r.a.createElement("option",{key:e.id,value:e.id},e.name)})))),r.a.createElement(z.a.Group,{as:I.a,controlId:"formStatus"},r.a.createElement(z.a.Label,null,"St\xe1tusz"),r.a.createElement(z.a.Control,{className:"bg-dark text-white",as:"select",name:"statusId",value:c,onChange:this.projectChange},this.state.statusList.map((function(e){return r.a.createElement("option",{key:e.id,value:e.id},e.name)}))))),r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formDescription"},r.a.createElement(z.a.Label,null,"Projekt le\xedr\xe1sa"),r.a.createElement(z.a.Control,{as:"textarea",rows:"10",value:d,onChange:this.projectChange,placeholder:"Add meg a projekt le\xedr\xe1s\xe1t.",className:"bg-dark text-white",name:"description"})))),r.a.createElement(C.a.Footer,null,r.a.createElement(m.b,{to:"/projects"},r.a.createElement(w.a,{variant:"primary"},"Vissza")),r.a.createElement("div",{className:"formButtonsRight"},r.a.createElement(w.a,{size:"bg",variant:"success",type:"submit"},"Ment\xe9s"),this.state.id?null:r.a.createElement(w.a,{className:"defaultButtonMarginLeft",size:"bg",variant:"info",type:"reset"},"Alaphelyzet")))))}}]),a}(n.Component)),D=function(e){Object(b.a)(a,e);var t=Object(g.a)(a);function a(e){var n;return Object(p.a)(this,a),(n=t.call(this,e)).deleteCustomer=function(e){N.a.delete(x("api/project_manager/customers/"+e)).then((function(t){200===t.status&&(alert("A megrendel\u0151 t\xf6r\xf6lve!"),n.setState({customers:n.state.customers.filter((function(t){return t.id!==e}))}))}))},n.state={customers:[]},n}return Object(E.a)(a,[{key:"componentDidMount",value:function(){var e=this;N.a.get(x("api/project_manager/customers")).then((function(e){return e.data})).then((function(t){return e.setState({customers:t})}))}},{key:"render",value:function(){var e=this;return r.a.createElement(C.a,{className:"border border-dark bg-dark text-white"},r.a.createElement(C.a.Header,null,"Megrendel\u0151k"),r.a.createElement(C.a.Body,null,r.a.createElement(m.b,{to:"/customers/add"},r.a.createElement(w.a,{variant:"success"},"Megrendel\u0151 hozz\xe1ad\xe1sa")),r.a.createElement(S.a,{className:"marginTop",bordered:!0,hover:!0,striped:!0,variant:"dark"},r.a.createElement("thead",null,r.a.createElement("tr",null,r.a.createElement("th",null,"N\xe9v"),r.a.createElement("th",null,"Telefonsz\xe1m"),r.a.createElement("th",null,"E-mail"),r.a.createElement("th",null,"Weboldal"),r.a.createElement("th",null,"Ir\xe1ny\xedt\xf3sz\xe1m"),r.a.createElement("th",null,"Helys\xe9g"),r.a.createElement("th",null,"Utca, h\xe1zsz\xe1m"),r.a.createElement("th",null))),r.a.createElement("tbody",null,0===this.state.customers.length?r.a.createElement("tr",{align:"center"},r.a.createElement("td",{colSpan:"10"},"Nincs el\xe9rhet\u0151 megrendel\u0151.")):this.state.customers.sort((function(e,t){return e.id-t.id})).map((function(t){return r.a.createElement("tr",{key:t.id},r.a.createElement("td",null,t.name),r.a.createElement("td",null,t.phone),r.a.createElement("td",null,t.email),r.a.createElement("td",null,t.website),r.a.createElement("td",null,t.zipCode),r.a.createElement("td",null,t.locality),r.a.createElement("td",null,t.streetAddress),r.a.createElement("td",null,r.a.createElement(m.b,{to:"customers/edit/"+t.id,className:"mr-2 btn btn-sm btn-primary"},"M\xf3dos\xedt"),r.a.createElement(w.a,{variant:"danger",onClick:e.deleteCustomer.bind(e,t.id),size:"sm"},"T\xf6r\xf6l")))}))))))}}]),a}(n.Component),M=(a(91),function(e){Object(b.a)(a,e);var t=Object(g.a)(a);function a(e){var n;return Object(p.a)(this,a),(n=t.call(this,e)).initialState={id:"",name:"",phone:"",email:"",website:"",zipCode:"",locality:"",streetAddress:""},n.resetCustomer=function(){n.setState((function(){return n.initialState}))},n.customerList=function(){return n.props.history.push("/customers")},n.state={id:"",name:"",phone:"",email:"",website:"",zipCode:"",locality:"",streetAddress:""},n.customerChange=n.customerChange.bind(Object(y.a)(n)),n.onSubmit=n.onSubmit.bind(Object(y.a)(n)),n}return Object(E.a)(a,[{key:"componentDidMount",value:function(){var e=+this.props.match.params.id;e&&this.findCustomerById(e)}},{key:"findCustomerById",value:function(e){var t=this;N.a.get(x("api/project_manager/customers/id/"+e)).then((function(e){200===e.status&&t.setState({id:e.data.id,name:e.data.name,phone:e.data.phone,email:e.data.email,website:e.data.website,zipCode:e.data.zipCode,locality:e.data.locality,streetAddress:e.data.streetAddress})})).catch((function(e){console.error("Hiba - "+e)}))}},{key:"onSubmit",value:function(e){if(e.preventDefault(),this.checkDetails()){var t={name:this.state.name,phone:this.state.phone,email:this.state.email,website:this.state.website,zipCode:this.state.zipCode,locality:this.state.locality,streetAddress:this.state.streetAddress};this.state.id?this.editCustomer(t):this.addCustomer(t)}}},{key:"addCustomer",value:function(e){var t=this;N.a.post(x("api/project_manager/customers"),e).then((function(e){200===e.status&&(t.resetCustomer(),alert("A megrendel\u0151 elmentve!"),t.customerList())}))}},{key:"editCustomer",value:function(e){var t=this;N.a.put(x("api/project_manager/customers/"+this.state.id),e).then((function(e){200===e.status&&(t.resetCustomer(),alert("A megrendel\u0151 elmentve!"),t.customerList())}))}},{key:"checkDetails",value:function(){if(this.state.name.length>=5)if(this.state.phone.length>=5)if(this.state.email.length>=5)if(this.state.website.length>=5)if(this.state.zipCode.length>=3)if(this.state.locality.length>=3){if(this.state.streetAddress.length>=5)return!0;alert("Az utca, h\xe1zsz\xe1m nem lehet r\xf6videbb 5 karaktern\xe9l!")}else alert("A helys\xe9g neve nem lehet r\xf6videbb 3 karaktern\xe9l!");else alert("Az ir\xe1ny\xedt\xf3sz\xe1m nem lehet r\xf6videbb 3 karaktern\xe9l!");else alert("A weboldal nem lehet r\xf6videbb 5 karaktern\xe9l!");else alert("Az email nem lehet r\xf6videbb 5 karaktern\xe9l!");else alert("A telefonsz\xe1m nem lehet r\xf6videbb 5 karaktern\xe9l!");else alert("A n\xe9v nem lehet r\xf6videbb 5 karaktern\xe9l!");return!1}},{key:"customerChange",value:function(e){this.setState(Object(k.a)({},e.target.name,e.target.value))}},{key:"render",value:function(){var e=this.state,t=e.name,a=e.phone,n=e.email,l=e.website,o=e.zipCode,s=e.locality,i=e.streetAddress;return r.a.createElement(C.a,{id:"customerFormContainer",className:"border border-dark bg-dark text-white"},r.a.createElement(C.a.Header,null,this.state.id?"Megrendel\u0151 m\xf3dos\xedt\xe1sa":"Megrendel\u0151 hozz\xe1ad\xe1sa"),r.a.createElement(z.a,{onReset:this.resetCustomer,onSubmit:this.onSubmit,id:"customerForm"},r.a.createElement(C.a.Body,null,r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formName"},r.a.createElement(z.a.Label,null,"Megrendel\u0151 neve"),r.a.createElement(z.a.Control,{required:!0,type:"text",name:"name",value:t,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a megrendel\u0151 nev\xe9t"})),r.a.createElement(z.a.Group,{as:I.a,controlId:"formName"},r.a.createElement(z.a.Label,null,"Megrendel\u0151 telefonsz\xe1ma"),r.a.createElement(z.a.Control,{required:!0,type:"text",name:"phone",value:a,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a megrendel\u0151 telefonsz\xe1m\xe1t"}))),r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formName"},r.a.createElement(z.a.Label,null,"Megrendel\u0151 email c\xedme"),r.a.createElement(z.a.Control,{required:!0,type:"text",name:"email",value:n,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a megrendel\u0151 email c\xedm\xe9t"})),r.a.createElement(z.a.Group,{as:I.a,controlId:"formName"},r.a.createElement(z.a.Label,null,"Megrendel\u0151 weboldala"),r.a.createElement(z.a.Control,{required:!0,type:"text",name:"website",value:l,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a megrendel\u0151 weboldal\xe1t"}))),r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formName"},r.a.createElement(z.a.Label,null,"Megrendel\u0151 ir\xe1ny\xedt\xf3sz\xe1ma"),r.a.createElement(z.a.Control,{required:!0,type:"text",name:"zipCode",value:o,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a megrendel\u0151 ir\xe1ny\xedt\xf3sz\xe1m\xe1t"})),r.a.createElement(z.a.Group,{as:I.a,controlId:"formName"},r.a.createElement(z.a.Label,null,"Helys\xe9g neve"),r.a.createElement(z.a.Control,{required:!0,type:"text",name:"locality",value:s,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a helys\xe9g nev\xe9t"}))),r.a.createElement(z.a.Row,null,r.a.createElement(z.a.Group,{as:I.a,controlId:"formName"},r.a.createElement(z.a.Label,null,"Utca, h\xe1zsz\xe1m"),r.a.createElement(z.a.Control,{required:!0,type:"text",name:"streetAddress",value:i,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg az utc\xe1t \xe9s a h\xe1zsz\xe1mot"})))),r.a.createElement(C.a.Footer,null,r.a.createElement(m.b,{to:"/customers"},r.a.createElement(w.a,{variant:"primary"},"Vissza")),r.a.createElement("div",{className:"formButtonsRight"},r.a.createElement(w.a,{size:"bg",variant:"success",type:"submit"},"Ment\xe9s"),this.state.id?null:r.a.createElement(w.a,{className:"defaultButtonMarginLeft",size:"bg",variant:"info",type:"reset"},"Alaphelyzet")))))}}]),a}(n.Component)),B=(a(92),function(e){Object(b.a)(a,e);var t=Object(g.a)(a);function a(){return Object(p.a)(this,a),t.apply(this,arguments)}return Object(E.a)(a,[{key:"render",value:function(){return r.a.createElement("div",{id:"notFound"},r.a.createElement("h1",null,"A keresett oldal nem tal\xe1lhat\xf3!"),r.a.createElement("h3",null,r.a.createElement(m.b,{to:"/projects"},"Vissza a f\u0151oldalra")))}}]),a}(n.Component));var G=function(){return r.a.createElement(m.a,null,r.a.createElement(j,null),r.a.createElement(s.a,{className:"marginTop",fluid:!0},r.a.createElement(i.a,null,r.a.createElement(c.a,{lg:12},r.a.createElement(d.c,null,r.a.createElement(d.a,{path:"/login",exact:!0,component:A}),r.a.createElement(h,{path:["/","/projects"],exact:!0,component:O}),r.a.createElement(h,{path:"/projects/add",exact:!0,component:P}),r.a.createElement(h,{path:"/projects/edit/:id",exact:!0,component:P}),r.a.createElement(h,{path:"/customers",exact:!0,component:D}),r.a.createElement(h,{path:"/customers/add",exact:!0,component:M}),r.a.createElement(h,{path:"/customers/edit/:id",exact:!0,component:M}),r.a.createElement(d.a,{exact:!0,component:B}))))))};Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));o.a.render(r.a.createElement(r.a.StrictMode,null,r.a.createElement(G,null)),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()})).catch((function(e){console.error(e.message)}))}},[[58,1,2]]]);
//# sourceMappingURL=main.330a8120.chunk.js.map