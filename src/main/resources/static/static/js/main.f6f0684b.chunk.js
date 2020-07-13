(this["webpackJsonppmrendszer-react"]=this["webpackJsonppmrendszer-react"]||[]).push([[0],{57:function(e,t,a){e.exports=a(88)},63:function(e,t,a){},64:function(e,t,a){},88:function(e,t,a){"use strict";a.r(t);var r=a(0),n=a.n(r),l=a(23),o=a.n(l),s=(a(62),a(63),a(64),a(93)),c=a(94),i=a(95),m=a(9),u=a(6),d=a(15),p=a(16),h=a(18),E=a(17),g=a(97),b=a(98),f=function(e){Object(h.a)(a,e);var t=Object(E.a)(a);function a(){return Object(d.a)(this,a),t.apply(this,arguments)}return Object(p.a)(a,[{key:"render",value:function(){return n.a.createElement(g.a,{bg:"dark",variant:"dark"},n.a.createElement(m.b,{to:"/",className:"navbar-brand"},"Projektmenedzsment rendszer"),n.a.createElement(g.a.Toggle,{"aria-controls":"basic-navbar-nav"}),n.a.createElement(g.a.Collapse,{id:"basic-navbar-nav"},n.a.createElement(b.a,{className:"mr-auto"},n.a.createElement(m.b,{to:"/projects",className:"nav-link"},"Projektek"),n.a.createElement(m.b,{to:"/customers",className:"nav-link"},"Megrendel\u0151k"))),n.a.createElement(g.a.Collapse,{className:"justify-content-end"},n.a.createElement(g.a.Text,null,"Bel\xe9pve: ",n.a.createElement("a",{href:"/"}," Viktor"))))}}]),a}(n.a.Component),j=a(21),v=a(99),y=a(96),C=a(55),k=a(91),w=a(8),x=a.n(w),z=function(e){Object(h.a)(a,e);var t=Object(E.a)(a);function a(e){var r;return Object(d.a)(this,a),(r=t.call(this,e)).initialState={id:"",name:"",customer:{},orderDate:"",deadline:"",developmentArea:{},projectStatus:{},priority:{},status:{},description:""},r.findAllLists=function(){x.a.get("http://localhost:8080/api/project_manager/customers").then((function(e){return e.data})).then((function(e){return r.lists.customerList=e})),x.a.get("http://localhost:8080/api/project_manager/development_areas").then((function(e){return e.data})).then((function(e){return r.lists.developmentAreaList=e})),x.a.get("http://localhost:8080/api/project_manager/project_statuses").then((function(e){return e.data})).then((function(e){return r.lists.projectStatusList=e})),x.a.get("http://localhost:8080/api/project_manager/priorities").then((function(e){return e.data})).then((function(e){return r.lists.priorityList=e})),x.a.get("http://localhost:8080/api/project_manager/statuses").then((function(e){return e.data})).then((function(e){return r.lists.statusList=e}))},r.findProjectById=function(e){x.a.get("http://localhost:8080/api/project_manager/projects/id/"+e).then((function(e){200===e.status&&r.setState({id:e.data.id,name:e.data.name,customer:e.data.customer,orderDate:e.data.orderDate,deadline:e.data.deadline,developmentArea:e.data.developmentArea,projectStatus:e.data.projectStatus,priority:e.data.priority,status:e.data.status,description:e.data.description})})).catch((function(e){console.error("Hiba - "+e)}))},r.resetProject=function(){r.setState((function(){return r.initialState}))},r.submitProject=function(e){e.preventDefault();var t={name:r.state.name,customer:r.state.customer,orderDate:r.state.orderDate,deadline:r.state.deadline,developmentArea:r.state.developmentArea,projectStatus:r.state.projectStatus,priority:r.state.priority,status:r.state.status,description:r.state.description};x.a.post("http://localhost:8080/api/project_manager/projects",t).then((function(e){200===e.status&&(r.setState(r.initialState),alert("A project elmentve!"))}))},r.updateProject=function(e){e.preventDefault();var t={name:r.state.name,customer:r.state.customer,orderDate:r.state.orderDate,deadline:r.state.deadline,developmentArea:r.state.developmentArea,projectStatus:r.state.projectStatus,priority:r.state.priority,status:r.state.status,description:r.state.description};x.a.put("http://localhost:8080/api/project_manager/projects/"+r.state.id,t).then((function(e){200===e.status&&(r.setState(r.initialState),alert("A project elmentve!"),r.projectList())}))},r.projectChange=function(e){r.setState({name:e.target.value,customer:r.getObject(r.lists.customerList,e.target.value),orderDate:e.target.value,deadline:e.target.value,developmentArea:r.getObject(r.lists.developmentAreaList,e.target.value),projectStatus:r.getObject(r.lists.projectStatusList,e.target.value),priority:r.getObject(r.lists.priorityList,e.target.value),status:r.getObject(r.lists.statusList,e.target.value),description:e.target.value}),console.log(r.state)},r.projectList=function(){return r.props.history.push("/projects")},r.getObject=function(e,t){for(var a=0;a<e.length;a++)if(e[a].id===t)return e[a];return null},r.getIndex=function(e,t){for(var a=0;a<e.length;a++)if(e[a].id===t.id)return a+1;return 0},r.state=r.initialState,r.lists={customerList:[],developmentAreaList:[],projectStatusList:[],priorityList:[],statusList:[]},r.findAllLists(),r.projectChange=r.projectChange.bind(Object(j.a)(r)),r.submitProject=r.submitProject.bind(Object(j.a)(r)),r}return Object(p.a)(a,[{key:"componentDidMount",value:function(){var e=+this.props.match.params.id;e&&this.findProjectById(e)}},{key:"render",value:function(){var e=this.state,t=e.name,a=e.orderDate,r=e.deadline,l=e.description;return n.a.createElement(v.a,{style:{width:"75%",margin:"0px auto"},className:"border border-dark bg-dark text-white"},n.a.createElement(v.a.Header,null," ",this.state.id?"Projekt m\xf3dos\xedt\xe1sa":"Projekt hozz\xe1ad\xe1sa"," "),n.a.createElement(y.a,{onReset:this.resetProject,onSubmit:this.state.id?this.updateProject:this.submitProject,id:"projektForm"},n.a.createElement(v.a.Body,null,n.a.createElement(y.a.Row,null,n.a.createElement(y.a.Group,{as:C.a,controlId:"formName"},n.a.createElement(y.a.Label,null,"Projekt neve"),n.a.createElement(y.a.Control,{required:!0,type:"text",name:"name",value:t,autoComplete:"off",onChange:this.projectChange,className:"bg-dark text-white",placeholder:"Add meg a projekt nev\xe9t"})),n.a.createElement(y.a.Group,{as:C.a,controlId:"formCustomer"},n.a.createElement(y.a.Label,null,"Megrendel\u0151"),n.a.createElement(y.a.Control,{className:"bg-dark text-white",as:"select",name:"customer",value:this.getIndex(this.lists.customerList,this.state.customer),onChange:this.projectChange},this.lists.customerList.map((function(e){return n.a.createElement("option",{key:e.id,value:e.id},e.name)}))))),n.a.createElement(y.a.Row,null,n.a.createElement(y.a.Group,{as:C.a,controlId:"formOrderDate"},n.a.createElement(y.a.Label,null,"Megrendel\xe9s d\xe1tuma"),n.a.createElement(y.a.Control,{required:!0,value:a,onChange:this.projectChange,type:"date",className:"bg-dark text-white",name:"orderDate"})),n.a.createElement(y.a.Group,{as:C.a,controlId:"formDeadline"},n.a.createElement(y.a.Label,null,"Hat\xe1rid\u0151 d\xe1tuma"),n.a.createElement(y.a.Control,{required:!0,value:r,onChange:this.projectChange,type:"date",className:"bg-dark text-white",name:"deadline"}))),n.a.createElement(y.a.Row,null,n.a.createElement(y.a.Group,{as:C.a,controlId:"formDevelopmentArea"},n.a.createElement(y.a.Label,null,"Fejleszt\xe9si ter\xfclet"),n.a.createElement(y.a.Control,{className:"bg-dark text-white",as:"select",name:"developmentArea",value:this.getIndex(this.lists.developmentAreaList,this.state.developmentArea),onChange:this.projectChange},this.lists.developmentAreaList.map((function(e){return n.a.createElement("option",{key:e.id,value:e.id},e.name)})))),n.a.createElement(y.a.Group,{as:C.a,controlId:"formProjectStatus"},n.a.createElement(y.a.Label,null,"Projekt \xe1llapot"),n.a.createElement(y.a.Control,{className:"bg-dark text-white",as:"select",name:"projectStatus",value:this.getIndex(this.lists.projectStatusList,this.state.projectStatus),onChange:this.projectChange},this.lists.projectStatusList.map((function(e){return n.a.createElement("option",{key:e.id,value:e.id},e.name)}))))),n.a.createElement(y.a.Row,null,n.a.createElement(y.a.Group,{as:C.a,controlId:"formPriority"},n.a.createElement(y.a.Label,null,"Priorit\xe1s"),n.a.createElement(y.a.Control,{className:"bg-dark text-white",as:"select",name:"priority",value:this.getIndex(this.lists.priorityList,this.state.priority),onChange:this.projectChange},this.lists.priorityList.map((function(e){return n.a.createElement("option",{key:e.id,value:e.id},e.name)})))),n.a.createElement(y.a.Group,{as:C.a,controlId:"formStatus"},n.a.createElement(y.a.Label,null,"St\xe1tusz"),n.a.createElement(y.a.Control,{className:"bg-dark text-white",as:"select",name:"status",value:this.getIndex(this.lists.statusList,this.state.status),onChange:this.projectChange},this.lists.statusList.map((function(e){return n.a.createElement("option",{key:e.id,value:e.id},e.name)}))))),n.a.createElement(y.a.Row,null,n.a.createElement(y.a.Group,{as:C.a,controlId:"formDescription"},n.a.createElement(y.a.Label,null,"Projekt le\xedr\xe1sa"),n.a.createElement(y.a.Control,{as:"textarea",rows:"10",value:l,onChange:this.projectChange,placeholder:"Add meg a projekt le\xedr\xe1s\xe1t.",className:"bg-dark text-white",name:"description"})))),n.a.createElement(v.a.Footer,null,n.a.createElement(m.b,{to:"/projects"},n.a.createElement(k.a,{variant:"primary"},"Vissza")),n.a.createElement("div",{style:{display:"inline",float:"right"}},n.a.createElement(k.a,{size:"bg",variant:"success",type:"submit"},"Ment\xe9s"),n.a.createElement(k.a,{style:{marginLeft:"10px"},size:"bg",variant:"info",type:"reset"},"Alaphelyzet")))))}}]),a}(r.Component),L=a(92),S=function(e){Object(h.a)(a,e);var t=Object(E.a)(a);function a(e){var r;return Object(d.a)(this,a),(r=t.call(this,e)).deleteProject=function(e){x.a.delete("/api/project_manager/projects/"+e).then((function(t){null!=t.data&&(alert("A projekt t\xf6r\xf6lve!"),r.setState({projects:r.state.projects.filter((function(t){return t.id!==e}))}))}))},r.state={projects:[]},r}return Object(p.a)(a,[{key:"componentDidMount",value:function(){var e=this;x.a.get("/api/project_manager/projects").then((function(e){return e.data})).then((function(t){return e.setState({projects:t})}))}},{key:"render",value:function(){var e=this;return n.a.createElement(v.a,{className:"border border-dark bg-dark text-white"},n.a.createElement(v.a.Header,null,"Projektek"),n.a.createElement(v.a.Body,null,n.a.createElement(m.b,{to:"/projects/add"},n.a.createElement(k.a,{variant:"success",disabled:!0},"Projekt hozz\xe1ad\xe1sa")),n.a.createElement(L.a,{bordered:!0,hover:!0,striped:!0,variant:"dark",style:{marginTop:"20px"}},n.a.createElement("thead",null,n.a.createElement("tr",null,n.a.createElement("th",null,"N\xe9v"),n.a.createElement("th",null,"Megrendel\u0151"),n.a.createElement("th",null,"Megrendel\xe9s d\xe1tuma"),n.a.createElement("th",null,"Hat\xe1rid\u0151 d\xe1tuma"),n.a.createElement("th",null,"Fejleszt\xe9si ter\xfclet"),n.a.createElement("th",null,"Projekt \xe1llapot"),n.a.createElement("th",null,"Priorit\xe1s"),n.a.createElement("th",null,"Projektvezet\u0151"),n.a.createElement("th",null,"St\xe1tusz"),n.a.createElement("th",null,"Gombok"))),n.a.createElement("tbody",null,0===this.state.projects.length?n.a.createElement("tr",{align:"center"},n.a.createElement("td",{colSpan:"10"},"Nincs el\xe9rhet\u0151 projekt.")):this.state.projects.map((function(t){return n.a.createElement("tr",{key:t.id},n.a.createElement("td",null,t.name),n.a.createElement("td",null,t.customer.name),n.a.createElement("td",null,t.developmentArea.name),n.a.createElement("td",null,t.orderDate),n.a.createElement("td",null,t.deadline),n.a.createElement("td",null,t.projectStatus.name),n.a.createElement("td",null,t.priority.name),n.a.createElement("td",null,t.projectLeader.name),n.a.createElement("td",null,t.status.name),n.a.createElement("td",null,n.a.createElement(m.b,{to:"projects/edit/"+t.id,className:"mr-2 btn btn-sm btn-primary disabled"},"M\xf3dos\xedt"),n.a.createElement(k.a,{variant:"danger",onClick:e.deleteProject.bind(e,t.id),size:"sm"},"T\xf6r\xf6l")))}))))))}}]),a}(r.Component),A=function(e){Object(h.a)(a,e);var t=Object(E.a)(a);function a(e){var r;return Object(d.a)(this,a),(r=t.call(this,e)).deleteCustomer=function(e){x.a.delete("/api/project_manager/customers/"+e).then((function(t){null!=t.data&&(alert("A megrendel\u0151 t\xf6r\xf6lve!"),r.setState({customers:r.state.customers.filter((function(t){return t.id!==e}))}))}))},r.state={customers:[]},r}return Object(p.a)(a,[{key:"componentDidMount",value:function(){var e=this;x.a.get("/api/project_manager/customers").then((function(e){return e.data})).then((function(t){return e.setState({customers:t})}))}},{key:"render",value:function(){var e=this;return n.a.createElement(v.a,{className:"border border-dark bg-dark text-white"},n.a.createElement(v.a.Header,null,"Megrendel\u0151k"),n.a.createElement(v.a.Body,null,n.a.createElement(m.b,{to:"/customers/add"},n.a.createElement(k.a,{variant:"success"},"Megrendel\u0151 hozz\xe1ad\xe1sa")),n.a.createElement(L.a,{bordered:!0,hover:!0,striped:!0,variant:"dark",style:{marginTop:"20px"}},n.a.createElement("thead",null,n.a.createElement("tr",null,n.a.createElement("th",null,"N\xe9v"),n.a.createElement("th",null,"Telefonsz\xe1m"),n.a.createElement("th",null,"E-mail"),n.a.createElement("th",null,"Weboldal"),n.a.createElement("th",null,"Ir\xe1ny\xedt\xf3sz\xe1m"),n.a.createElement("th",null,"Helys\xe9g"),n.a.createElement("th",null,"Utca, h\xe1zsz\xe1m"),n.a.createElement("th",null,"Gombok"))),n.a.createElement("tbody",null,0===this.state.customers.length?n.a.createElement("tr",{align:"center"},n.a.createElement("td",{colSpan:"10"},"Nincs el\xe9rhet\u0151 megrendel\u0151.")):this.state.customers.map((function(t){return n.a.createElement("tr",{key:t.id},n.a.createElement("td",null,t.name),n.a.createElement("td",null,t.phone),n.a.createElement("td",null,t.email),n.a.createElement("td",null,t.website),n.a.createElement("td",null,t.zipCode),n.a.createElement("td",null,t.locality),n.a.createElement("td",null,t.streetAddress),n.a.createElement("td",null,n.a.createElement(m.b,{to:"customers/edit/"+t.id,className:"mr-2 btn btn-sm btn-primary"},"M\xf3dos\xedt"),n.a.createElement(k.a,{variant:"danger",onClick:e.deleteCustomer.bind(e,t.id),size:"sm"},"T\xf6r\xf6l")))}))))))}}]),a}(r.Component),N=a(56),O=function(e){Object(h.a)(a,e);var t=Object(E.a)(a);function a(e){var r;return Object(d.a)(this,a),(r=t.call(this,e)).initialState={id:"",name:"",phone:"",email:"",website:"",zipCode:"",locality:"",streetAddress:""},r.findProjectById=function(e){x.a.get("/api/project_manager/customers/id/"+e).then((function(e){null!=e.data&&r.setState({id:e.data.id,name:e.data.name,phone:e.data.phone,email:e.data.email,website:e.data.website,zipCode:e.data.zipCode,locality:e.data.locality,streetAddress:e.data.streetAddress})})).catch((function(e){console.error("Hiba - "+e)}))},r.resetCustomer=function(){r.setState((function(){return r.initialState}))},r.submitCustomer=function(e){e.preventDefault();var t={name:r.state.name,phone:r.state.phone,email:r.state.email,website:r.state.website,zipCode:r.state.zipCode,locality:r.state.locality,streetAddress:r.state.streetAddress};x.a.post("/api/project_manager/customers",t).then((function(e){200===e.status&&(r.setState(r.initialState),alert("A megrendel\u0151 elmentve!"))}))},r.updateCustomer=function(e){e.preventDefault();var t={name:r.state.name,phone:r.state.phone,email:r.state.email,website:r.state.website,zipCode:r.state.zipCode,locality:r.state.locality,streetAddress:r.state.streetAddress};x.a.put("/api/project_manager/customers/"+r.state.id,t).then((function(e){200===e.status&&(r.setState(r.initialState),alert("A megrendel\u0151 elmentve!"),r.customerList())}))},r.customerChange=function(e){r.setState(Object(N.a)({},e.target.name,e.target.value))},r.customerList=function(){return r.props.history.push("/customers")},r.state=r.initialState,r.customerChange=r.customerChange.bind(Object(j.a)(r)),r.submitCustomer=r.submitCustomer.bind(Object(j.a)(r)),r}return Object(p.a)(a,[{key:"componentDidMount",value:function(){var e=+this.props.match.params.id;e&&this.findProjectById(e)}},{key:"render",value:function(){var e=this.state,t=e.name,a=e.phone,r=e.email,l=e.website,o=e.zipCode,s=e.locality,c=e.streetAddress;return n.a.createElement(v.a,{style:{width:"75%",margin:"0px auto"},className:"border border-dark bg-dark text-white"},n.a.createElement(v.a.Header,null,this.state.id?"Megrendel\u0151 m\xf3dos\xedt\xe1sa":"Megrendel\u0151 hozz\xe1ad\xe1sa"),n.a.createElement(y.a,{onReset:this.resetCustomer,onSubmit:this.state.id?this.updateCustomer:this.submitCustomer,id:"customerForm"},n.a.createElement(v.a.Body,null,n.a.createElement(y.a.Row,null,n.a.createElement(y.a.Group,{as:C.a,controlId:"formName"},n.a.createElement(y.a.Label,null,"Megrendel\u0151 neve"),n.a.createElement(y.a.Control,{required:!0,type:"text",name:"name",value:t,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a megrendel\u0151 nev\xe9t"})),n.a.createElement(y.a.Group,{as:C.a,controlId:"formName"},n.a.createElement(y.a.Label,null,"Megrendel\u0151 telefonsz\xe1ma"),n.a.createElement(y.a.Control,{required:!0,type:"text",name:"phone",value:a,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a megrendel\u0151 telefonsz\xe1m\xe1t"}))),n.a.createElement(y.a.Row,null,n.a.createElement(y.a.Group,{as:C.a,controlId:"formName"},n.a.createElement(y.a.Label,null,"Megrendel\u0151 email c\xedme"),n.a.createElement(y.a.Control,{required:!0,type:"text",name:"email",value:r,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a megrendel\u0151 email c\xedm\xe9t"})),n.a.createElement(y.a.Group,{as:C.a,controlId:"formName"},n.a.createElement(y.a.Label,null,"Megrendel\u0151 weboldala"),n.a.createElement(y.a.Control,{required:!0,type:"text",name:"website",value:l,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a megrendel\u0151 weboldal\xe1t"}))),n.a.createElement(y.a.Row,null,n.a.createElement(y.a.Group,{as:C.a,controlId:"formName"},n.a.createElement(y.a.Label,null,"Megrendel\u0151 ir\xe1ny\xedt\xf3sz\xe1ma"),n.a.createElement(y.a.Control,{required:!0,type:"text",name:"zipCode",value:o,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a megrendel\u0151 ir\xe1ny\xedt\xf3sz\xe1m\xe1t"})),n.a.createElement(y.a.Group,{as:C.a,controlId:"formName"},n.a.createElement(y.a.Label,null,"Helys\xe9g neve"),n.a.createElement(y.a.Control,{required:!0,type:"text",name:"locality",value:s,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg a helys\xe9g nev\xe9t"}))),n.a.createElement(y.a.Row,null,n.a.createElement(y.a.Group,{as:C.a,controlId:"formName"},n.a.createElement(y.a.Label,null,"Utca, h\xe1zsz\xe1m"),n.a.createElement(y.a.Control,{required:!0,type:"text",name:"streetAddress",value:c,autoComplete:"off",onChange:this.customerChange,className:"bg-dark text-white",placeholder:"Add meg az utc\xe1t \xe9s a h\xe1zsz\xe1mot"})))),n.a.createElement(v.a.Footer,null,n.a.createElement(m.b,{to:"/customers"},n.a.createElement(k.a,{variant:"primary"},"Vissza")),n.a.createElement("div",{style:{display:"inline",float:"right"}},n.a.createElement(k.a,{size:"bg",variant:"success",type:"submit"},"Ment\xe9s"),n.a.createElement(k.a,{style:{marginLeft:"10px"},size:"bg",variant:"info",type:"reset"},"Alaphelyzet")))))}}]),a}(r.Component);var P=function(){return n.a.createElement(m.a,null,n.a.createElement(f,null),n.a.createElement(s.a,{fluid:!0},n.a.createElement(c.a,null,n.a.createElement(i.a,{lg:12,style:{marginTop:"20px"}},n.a.createElement(u.c,null,n.a.createElement(u.a,{path:"/",exact:!0,component:S}),n.a.createElement(u.a,{path:"/projects",exact:!0,component:S}),n.a.createElement(u.a,{path:"/projects/add",exact:!0,component:z}),n.a.createElement(u.a,{path:"/projects/edit/:id",exact:!0,component:z}),n.a.createElement(u.a,{path:"/customers",exact:!0,component:A}),n.a.createElement(u.a,{path:"/customers/add",exact:!0,component:O}),n.a.createElement(u.a,{path:"/customers/edit/:id",exact:!0,component:O}))))))};Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));o.a.render(n.a.createElement(n.a.StrictMode,null,n.a.createElement(P,null)),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then((function(e){e.unregister()})).catch((function(e){console.error(e.message)}))}},[[57,1,2]]]);
//# sourceMappingURL=main.f6f0684b.chunk.js.map