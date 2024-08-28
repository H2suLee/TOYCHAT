(function(){"use strict";var e={66:function(e,t,n){var o=n(5130),a=n(6768);const r={key:1},i={key:1};function l(e,t,n,l,u,c){const s=(0,a.g2)("Login"),d=(0,a.g2)("Header"),m=(0,a.g2)("LeftMenu"),f=(0,a.g2)("AdminLogin"),p=(0,a.g2)("AdminHeader"),v=(0,a.g2)("AdminLeftMenu"),g=(0,a.g2)("router-view");return(0,a.uX)(),(0,a.CE)("div",null,[(0,a.bo)((0,a.Lk)("div",null,[(0,a.Lk)("div",null,[c.isLogin?((0,a.uX)(),(0,a.CE)("div",r,[(0,a.bF)(d),(0,a.bF)(m)])):((0,a.uX)(),(0,a.Wv)(s,{key:0}))])],512),[[o.aG,!l.isAdmin]]),(0,a.bo)((0,a.Lk)("div",null,[(0,a.Lk)("div",null,[c.isLogin?((0,a.uX)(),(0,a.CE)("div",i,[(0,a.bF)(p),(0,a.bF)(v)])):((0,a.uX)(),(0,a.Wv)(f,{key:0}))])],512),[[o.aG,l.isAdmin]]),(0,a.bF)(g)])}n(4979);var u=n(144),c=n(973),s=n(4232);const d=(0,a.Lk)("p",null,"홍길동 접속중..",-1),m=(0,a.Lk)("p",null,"로그아웃",-1),f=[m],p=(0,a.Lk)("h2",null,"대화",-1),v=(0,a.Lk)("p",null,"질문이 있으신가요? 지금 문의하세요!",-1),g={key:0},k=(0,a.Lk)("p",null,"가능한 상담원이 없습니다.",-1),h=[k],b={key:1},L=(0,a.Lk)("p",null,"온라인 문의가 가능한 상태입니다.",-1),A=(0,a.Lk)("p",null,"안녕하세요! 어떻게 도와드릴까요?",-1);function y(e,t,n,o,r,i){const l=(0,a.g2)("ChatModal");return(0,a.uX)(),(0,a.CE)("div",null,[(0,a.eW)(" 헤더 "),d,(0,a.Lk)("a",{onClick:t[0]||(t[0]=(...e)=>o.fn_kakaoLogout&&o.fn_kakaoLogout(...e))},f),(0,a.Lk)("button",{onClick:t[1]||(t[1]=(...e)=>o.openChatModal&&o.openChatModal(...e))},"실시간 채팅 상담(챗 모달 팝업)"),(0,a.bF)(l,{modelValue:o.isModalVisible,"onUpdate:modelValue":t[2]||(t[2]=e=>o.isModalVisible=e),userId:o.userId,nick:o.nick,role:o.role,chatroomId:o.chatroomId},{default:(0,a.k6)((()=>[p,v,(0,a.Lk)("div",null,[o.isActivAdmin?((0,a.uX)(),(0,a.CE)("div",b,[L,((0,a.uX)(!0),(0,a.CE)(a.FK,null,(0,a.pI)(o.activeAdmin,((e,t)=>((0,a.uX)(),(0,a.CE)("div",{key:t},(0,s.v_)(e),1)))),128)),A])):((0,a.uX)(),(0,a.CE)("div",g,h))])])),_:1},8,["modelValue","userId","nick","role","chatroomId"])])}var C=n(5107),S=n(4373),E={components:{ChatModal:C.A},setup(){const e=(0,c.rd)(),t=(0,u.KR)(localStorage.getItem("id")),n=(0,u.KR)(localStorage.getItem("nick")),o=(0,u.KR)("USR"),r=(0,u.KR)(!1),i=(0,u.KR)("");let l=null,s=(0,u.KR)([]),d=(0,u.KR)(!1);const m=()=>{window.Kakao.Auth.logout((t=>{t&&(localStorage.setItem("isAuthenticated",!1),localStorage.setItem("id",""),localStorage.setItem("nick","")),e.go("/")}))},f=()=>{S.A.post("/api/chat/create",{id:t.value,nick:n.value}).then((e=>{i.value=e.data.chatroomId,r.value=!0}))},p=()=>{l=new WebSocket("ws://localhost:9090/ws/adminOnList"),l.onopen=()=>{console.log("activeAdminChkSocket connection opened")},l.onmessage=e=>{console.log("activeAdminChkSocket got message");let t=e.data,n=t.split(",");s.value=n,d.value=s.value[0].length>0},l.onclose=()=>{console.log("activeAdminChkSocket connection closed")},l.onerror=e=>{console.error("activeAdminChkSocket error: ",e)}};return(0,a.sV)((()=>{p()})),(0,a.hi)((()=>{l&&l.close()})),{userId:t,nick:n,role:o,isModalVisible:r,chatroomId:i,activeAdmin:s,isActivAdmin:d,fn_kakaoLogout:m,openChatModal:f,openActiveAdminChkSocket:p}}},w=n(1241);const I=(0,w.A)(E,[["render",y]]);var _=I;function W(e,t){const n=(0,a.g2)("router-link");return(0,a.uX)(),(0,a.CE)("div",null,[(0,a.eW)(" 왼쪽메뉴 "),(0,a.bF)(n,{to:"/chat/list"},{default:(0,a.k6)((()=>[(0,a.eW)("채팅 이력")])),_:1})])}const X={},K=(0,w.A)(X,[["render",W]]);var M=K;const O=(0,a.Lk)("img",{src:"//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg",width:"222"},null,-1),R=[O];function j(e,t,n,o,r,i){return(0,a.uX)(),(0,a.CE)("div",null,[(0,a.Lk)("a",{onClick:t[0]||(t[0]=(...e)=>i.fn_kakaoLogin&&i.fn_kakaoLogin(...e))},R)])}var N={methods:{fn_kakaoLogin(){window.Kakao.Auth.login({scope:"profile_nickname",success:this.fn_getKakaoAccount})},fn_getKakaoAccount(){window.Kakao.API.request({url:"/v2/user/me",success:e=>{var t=e.id,n=e.kakao_account.profile.nickname;localStorage.setItem("isAuthenticated",!0),localStorage.setItem("id",t),localStorage.setItem("nick",n),localStorage.setItem("role","USR"),this.$router.go("/chat/list")},fail:e=>{console.log(e),localStorage.setItem("isAuthenticated",!1)}})}}};const F=(0,w.A)(N,[["render",j]]);var V=F;function x(e,t){return(0,a.uX)(),(0,a.CE)("div",null,"user home")}const T={},D=(0,w.A)(T,[["render",x]]);var P=D;const $=(0,a.Lk)("p",null,"관리자 접속중..",-1);function H(e,t,n,o,r,i){return(0,a.uX)(),(0,a.CE)("div",null,[(0,a.eW)(" 관리자 헤더 "),$,(0,a.Lk)("a",{onClick:t[0]||(t[0]=(...e)=>o.handleLogout&&o.handleLogout(...e))},"로그아웃")])}var U={setup(){const e=(0,c.rd)(),t=()=>{localStorage.removeItem("jwt"),localStorage.removeItem("adminId"),localStorage.removeItem("adminNick"),e.go("/admin")};return{handleLogout:t}}};const q=(0,w.A)(U,[["render",H]]);var B=q;function J(e,t){const n=(0,a.g2)("router-link");return(0,a.uX)(),(0,a.CE)("div",null,[(0,a.eW)(" 왼쪽메뉴 "),(0,a.bF)(n,{to:"/admin/chat/mnglist"},{default:(0,a.k6)((()=>[(0,a.eW)("채팅관리")])),_:1}),(0,a.bF)(n,{to:"/admin/chat/serviceList"},{default:(0,a.k6)((()=>[(0,a.eW)("실시간 채팅 지원")])),_:1}),(0,a.bF)(n,{to:"/admin/chat/empty"},{default:(0,a.k6)((()=>[(0,a.eW)("빈페이지")])),_:1})])}const G={},Q=(0,w.A)(G,[["render",J]]);var Y=Q;const z=e=>((0,a.Qi)("data-v-9ed5a128"),e=e(),(0,a.jt)(),e),Z={class:"login"},ee=z((()=>(0,a.Lk)("h1",null,"Login",-1))),te=z((()=>(0,a.Lk)("label",{for:"username"},"Id:",-1))),ne=z((()=>(0,a.Lk)("label",{for:"password"},"Password:",-1))),oe=z((()=>(0,a.Lk)("button",{type:"submit"},"Login",-1))),ae={key:0,class:"error"};function re(e,t,n,r,i,l){return(0,a.uX)(),(0,a.CE)("div",Z,[ee,(0,a.Lk)("form",{onSubmit:t[2]||(t[2]=(0,o.D$)(((...e)=>l.handleLogin&&l.handleLogin(...e)),["prevent"]))},[(0,a.Lk)("div",null,[te,(0,a.bo)((0,a.Lk)("input",{type:"text","onUpdate:modelValue":t[0]||(t[0]=e=>i.id=e),id:"id",required:""},null,512),[[o.Jo,i.id]])]),(0,a.Lk)("div",null,[ne,(0,a.bo)((0,a.Lk)("input",{type:"password","onUpdate:modelValue":t[1]||(t[1]=e=>i.password=e),id:"password",required:""},null,512),[[o.Jo,i.password]])]),oe,i.error?((0,a.uX)(),(0,a.CE)("div",ae,(0,s.v_)(i.error),1)):(0,a.Q3)("",!0)],32)])}var ie={data(){return{id:"",password:"",error:""}},methods:{async handleLogin(){try{const e=await S.A.post("/api/admin/login",{id:this.id,pw:this.password}),t=e.data.jwt,n=e.data.nick;localStorage.setItem("jwt",t),localStorage.setItem("adminNick",n),localStorage.setItem("adminId",this.id),this.$router.go("/admin/chat/mnglist")}catch(e){this.error="Login failed: "+(e.response?.data?.message||"Unknown error"),this.$router.go("/admin")}}}};const le=(0,w.A)(ie,[["render",re],["__scopeId","data-v-9ed5a128"]]);var ue=le;function ce(e,t){return(0,a.uX)(),(0,a.CE)("div",null,"admin home")}const se={},de=(0,w.A)(se,[["render",ce]]);var me=de,fe={name:"App",components:{Header:_,LeftMenu:M,Login:V,AdminHeader:B,AdminLeftMenu:Y,AdminLogin:ue,Home:P,AdminHome:me},setup(){const e=(0,c.lq)(),t=(0,c.rd)(),n=(0,u.KR)(!1),o=()=>{n.value=e.path.startsWith("/admin")};return t.afterEach(((e,t)=>{o()})),{isAdmin:n}},mounted(){this.isAdmin=this.$route.path.startsWith("/admin")},computed:{isLogin(){const e="true"===localStorage.getItem("isAuthenticated");let t;const n=localStorage.getItem("jwt");n||(t=!1);try{const e=JSON.parse(atob(n.split(".")[1])),o=e.exp&&Date.now()/1e3>e.exp;t=!o}catch(o){t=!1}return this.isAdmin?t:e}}};const pe=(0,w.A)(fe,[["render",l]]);var ve=pe;const ge=[{path:"/",name:"Home",component:P},{path:"/chat/list",name:"ChatList",component:()=>n.e(215).then(n.bind(n,6215))},{path:"/admin",name:"AdminHome",component:me},{path:"/admin/chat/mnglist",name:"AdminChatManage",component:()=>n.e(617).then(n.bind(n,7617))},{path:"/admin/chat/serviceList",name:"AdminChatService",component:()=>n.e(574).then(n.bind(n,8574))},{path:"/admin/chat/empty",name:"AdminEmpty",component:()=>n.e(913).then(n.bind(n,2913))}],ke=(0,c.aE)({history:(0,c.LA)(),routes:ge});var he=ke;const be=(0,o.Ef)(ve).use(he);be.config.globalProperties.$axios=S.A,be.mount("#app")},5107:function(e,t,n){n.d(t,{A:function(){return d}});var o=n(6768),a=n(5130),r=n(4232);function i(e,t,n,i,l,u){return i.visible?((0,o.uX)(),(0,o.CE)("div",{key:0,class:"modal-overlay",onClick:t[7]||(t[7]=(0,a.D$)(((...e)=>i.close&&i.close(...e)),["self"]))},[(0,o.Lk)("div",{class:"modal-content",ref:"modalContent",onMousedown:t[4]||(t[4]=(...e)=>i.startDrag&&i.startDrag(...e)),onMouseup:t[5]||(t[5]=(...e)=>i.stopDrag&&i.stopDrag(...e)),onMousemove:t[6]||(t[6]=(...e)=>i.drag&&i.drag(...e))},[(0,o.Lk)("button",{class:"modal-close",onClick:t[0]||(t[0]=(...e)=>i.close&&i.close(...e))},"×"),(0,o.RG)(e.$slots,"default",{},void 0,!0),(0,o.bo)((0,o.Lk)("div",null,null,512),[[a.aG,"USR"==i.role]]),((0,o.uX)(!0),(0,o.CE)(o.FK,null,(0,o.pI)(i.messages,((e,t)=>((0,o.uX)(),(0,o.CE)("div",{key:t},[(0,o.Lk)("p",null,(0,r.v_)(e.nick)+" : "+(0,r.v_)(e.content),1)])))),128)),(0,o.Lk)("div",null,[(0,o.bo)((0,o.Lk)("input",{"onUpdate:modelValue":t[1]||(t[1]=e=>i.message=e),placeholder:"메시지 작성.."},null,512),[[a.Jo,i.message]]),(0,o.Lk)("button",{onClick:t[2]||(t[2]=(...e)=>i.sendMessage&&i.sendMessage(...e))},"전송"),(0,o.Lk)("button",{onClick:t[3]||(t[3]=(...e)=>i.close&&i.close(...e))},"종료")])],544)])):(0,o.Q3)("",!0)}n(4114);var l=n(144),u={props:{modelValue:{type:Boolean,default:!1},userId:{type:String,default:""},nick:{type:String,default:""},role:{type:String,default:""},chatroomId:{type:String,default:""}},setup(e,{emit:t}){const n=(0,o.EW)((()=>e.userId)),a=(0,o.EW)((()=>e.nick)),r=(0,o.EW)((()=>e.role));let i=(0,o.EW)((()=>e.chatroomId));const u=(0,l.KR)(e.modelValue),c=(0,l.KR)(null);let s=(0,l.KR)(!1),d=0,m=0,f=0,p=0;const v=(0,l.KR)("");let g=(0,l.KR)([]),k=null;const h=e=>{let t="ENTER"===e?`${a.value}님이 입장하였습니다.`:v.value;const o={chatroomId:i.value,id:n.value,nick:a.value,content:t,type:e};return o},b=()=>{A(h("TALK")),v.value=""},L=()=>{A(h("ENTER"))},A=e=>{k.readyState===WebSocket.OPEN?k.send(JSON.stringify(e)):console.error("WebSocket is not open")},y=()=>{k=new WebSocket("ws://localhost:9090/ws/chat"),k.onopen=()=>{console.log("WebSocket connection opened"),L("ENTER")},k.onmessage=e=>{let t=JSON.parse(e.data),n={nick:t.nick,content:t.content};g.value.push(n)},k.onclose=()=>{console.log("WebSocket connection closed")},k.onerror=e=>{console.error("WebSocket error: ",e)}},C=()=>{i.value="",g.value=[],k.close(),t("update:modelValue",!1)},S=e=>{s.value=!0,d=e.clientX,m=e.clientY;const t=c.value.getBoundingClientRect();f=t.left,p=t.top},E=()=>{s.value=!1},w=e=>{if(!s.value)return;const t=e.clientX-d,n=e.clientY-m;c.value.style.left=`${f+t}px`,c.value.style.top=`${p+n}px`};return(0,o.wB)((()=>e.modelValue),(e=>{u.value=e,e&&(null!==k&&k.readyState!==WebSocket.CLOSED||y())})),(0,o.sV)((()=>{})),{userId:n,nick:a,role:r,chatroomId:i,visible:u,close:C,modalContent:c,startDrag:S,stopDrag:E,drag:w,websocket:k,message:v,messages:g,sendWebSocket:A,sendMessage:b}}},c=n(1241);const s=(0,c.A)(u,[["render",i],["__scopeId","data-v-141ce9da"]]);var d=s}},t={};function n(o){var a=t[o];if(void 0!==a)return a.exports;var r=t[o]={exports:{}};return e[o].call(r.exports,r,r.exports,n),r.exports}n.m=e,function(){var e=[];n.O=function(t,o,a,r){if(!o){var i=1/0;for(s=0;s<e.length;s++){o=e[s][0],a=e[s][1],r=e[s][2];for(var l=!0,u=0;u<o.length;u++)(!1&r||i>=r)&&Object.keys(n.O).every((function(e){return n.O[e](o[u])}))?o.splice(u--,1):(l=!1,r<i&&(i=r));if(l){e.splice(s--,1);var c=a();void 0!==c&&(t=c)}}return t}r=r||0;for(var s=e.length;s>0&&e[s-1][2]>r;s--)e[s]=e[s-1];e[s]=[o,a,r]}}(),function(){n.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return n.d(t,{a:t}),t}}(),function(){n.d=function(e,t){for(var o in t)n.o(t,o)&&!n.o(e,o)&&Object.defineProperty(e,o,{enumerable:!0,get:t[o]})}}(),function(){n.f={},n.e=function(e){return Promise.all(Object.keys(n.f).reduce((function(t,o){return n.f[o](e,t),t}),[]))}}(),function(){n.u=function(e){return"js/"+e+"."+{215:"f199a73d",574:"a46e3d1b",617:"258da441",913:"ba9728fe"}[e]+".js"}}(),function(){n.miniCssF=function(e){return"css/"+e+"."+{215:"295b7dc8",617:"295b7dc8"}[e]+".css"}}(),function(){n.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){n.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){var e={},t="test1:";n.l=function(o,a,r,i){if(e[o])e[o].push(a);else{var l,u;if(void 0!==r)for(var c=document.getElementsByTagName("script"),s=0;s<c.length;s++){var d=c[s];if(d.getAttribute("src")==o||d.getAttribute("data-webpack")==t+r){l=d;break}}l||(u=!0,l=document.createElement("script"),l.charset="utf-8",l.timeout=120,n.nc&&l.setAttribute("nonce",n.nc),l.setAttribute("data-webpack",t+r),l.src=o),e[o]=[a];var m=function(t,n){l.onerror=l.onload=null,clearTimeout(f);var a=e[o];if(delete e[o],l.parentNode&&l.parentNode.removeChild(l),a&&a.forEach((function(e){return e(n)})),t)return t(n)},f=setTimeout(m.bind(null,void 0,{type:"timeout",target:l}),12e4);l.onerror=m.bind(null,l.onerror),l.onload=m.bind(null,l.onload),u&&document.head.appendChild(l)}}}(),function(){n.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){n.p="/"}(),function(){if("undefined"!==typeof document){var e=function(e,t,o,a,r){var i=document.createElement("link");i.rel="stylesheet",i.type="text/css",n.nc&&(i.nonce=n.nc);var l=function(n){if(i.onerror=i.onload=null,"load"===n.type)a();else{var o=n&&n.type,l=n&&n.target&&n.target.href||t,u=new Error("Loading CSS chunk "+e+" failed.\n("+o+": "+l+")");u.name="ChunkLoadError",u.code="CSS_CHUNK_LOAD_FAILED",u.type=o,u.request=l,i.parentNode&&i.parentNode.removeChild(i),r(u)}};return i.onerror=i.onload=l,i.href=t,o?o.parentNode.insertBefore(i,o.nextSibling):document.head.appendChild(i),i},t=function(e,t){for(var n=document.getElementsByTagName("link"),o=0;o<n.length;o++){var a=n[o],r=a.getAttribute("data-href")||a.getAttribute("href");if("stylesheet"===a.rel&&(r===e||r===t))return a}var i=document.getElementsByTagName("style");for(o=0;o<i.length;o++){a=i[o],r=a.getAttribute("data-href");if(r===e||r===t)return a}},o=function(o){return new Promise((function(a,r){var i=n.miniCssF(o),l=n.p+i;if(t(i,l))return a();e(o,l,null,a,r)}))},a={524:0};n.f.miniCss=function(e,t){var n={215:1,617:1};a[e]?t.push(a[e]):0!==a[e]&&n[e]&&t.push(a[e]=o(e).then((function(){a[e]=0}),(function(t){throw delete a[e],t})))}}}(),function(){var e={524:0};n.f.j=function(t,o){var a=n.o(e,t)?e[t]:void 0;if(0!==a)if(a)o.push(a[2]);else{var r=new Promise((function(n,o){a=e[t]=[n,o]}));o.push(a[2]=r);var i=n.p+n.u(t),l=new Error,u=function(o){if(n.o(e,t)&&(a=e[t],0!==a&&(e[t]=void 0),a)){var r=o&&("load"===o.type?"missing":o.type),i=o&&o.target&&o.target.src;l.message="Loading chunk "+t+" failed.\n("+r+": "+i+")",l.name="ChunkLoadError",l.type=r,l.request=i,a[1](l)}};n.l(i,u,"chunk-"+t,t)}},n.O.j=function(t){return 0===e[t]};var t=function(t,o){var a,r,i=o[0],l=o[1],u=o[2],c=0;if(i.some((function(t){return 0!==e[t]}))){for(a in l)n.o(l,a)&&(n.m[a]=l[a]);if(u)var s=u(n)}for(t&&t(o);c<i.length;c++)r=i[c],n.o(e,r)&&e[r]&&e[r][0](),e[r]=0;return n.O(s)},o=self["webpackChunktest1"]=self["webpackChunktest1"]||[];o.forEach(t.bind(null,0)),o.push=t.bind(null,o.push.bind(o))}();var o=n.O(void 0,[504],(function(){return n(66)}));o=n.O(o)})();
//# sourceMappingURL=app.89cee943.js.map