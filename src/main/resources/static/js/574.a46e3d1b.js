"use strict";(self["webpackChunktest1"]=self["webpackChunktest1"]||[]).push([[574],{8574:function(o,e,t){t.r(e),t.d(e,{default:function(){return C}});var l=t(6768),n=t(4232);const a=(0,l.Lk)("h1",null,"실시간 채팅 지원",-1),c={key:0},i={key:1},r=["onClick"];function s(o,e,t,s,d,u){const k=(0,l.g2)("ChatModal");return(0,l.uX)(),(0,l.CE)("div",null,[a,0===s.chatrooms.length?((0,l.uX)(),(0,l.CE)("div",c,"실시간 채팅 지원이 없습니다.")):((0,l.uX)(),(0,l.CE)("div",i,[(0,l.Lk)("table",null,[(0,l.Lk)("tbody",null,[((0,l.uX)(!0),(0,l.CE)(l.FK,null,(0,l.pI)(s.chatrooms,(o=>((0,l.uX)(),(0,l.CE)("tr",{key:o.chatroomId,onClick:e=>s.openChatModal(o._id)},[(0,l.Lk)("td",null,"rid: "+(0,n.v_)(o._id),1),(0,l.Lk)("td",null,"닉: "+(0,n.v_)(o.participants[0].nick),1),(0,l.Lk)("td",null,"생성일: "+(0,n.v_)(o.credt),1),(0,l.Lk)("td",null,"상태: "+(0,n.v_)(o.status),1)],8,r)))),128))])])])),(0,l.bF)(k,{modelValue:s.isModalVisible,"onUpdate:modelValue":e[0]||(e[0]=o=>s.isModalVisible=o),userId:s.userId,nick:s.nick,role:s.role,chatroomId:s.chatroomId},null,8,["modelValue","userId","nick","role","chatroomId"])])}var d=t(144),u=t(4373),k=t(5107),h={components:{ChatModal:k.A},setup(){const o=(0,d.KR)(localStorage.getItem("adminId")),e=(0,d.KR)(localStorage.getItem("adminNick")),t=(0,d.KR)("ADM"),n=(0,d.KR)(!1),a=(0,d.KR)(""),c=(0,d.KR)([]);let i=null;const r=o=>{a.value=o,n.value=!0},s=async()=>{try{const o=await u.A.post("/api/admin/chat/liveChatWaitingList",{});c.value=o.data,console.log("length: "+c.value.length)}catch(o){console.error("Error fetching chat list:",o)}},k=()=>{let o=`ws://localhost:9090/ws/adminOnList?nick=${e.value}`;i=new WebSocket(o),i.onopen=()=>{console.log("activeAdminChkSocket connection opened")},i.onclose=()=>{console.log("activeAdminChkSocket connection closed")},i.onerror=o=>{console.error("activeAdminChkSocket error:",o)}};return(0,l.sV)((()=>{k(),s()})),(0,l.hi)((()=>{i&&i.close()})),{userId:o,nick:e,role:t,chatrooms:c,chatroomId:a,isModalVisible:n,openChatModal:r}}},m=t(1241);const v=(0,m.A)(h,[["render",s]]);var C=v}}]);
//# sourceMappingURL=574.a46e3d1b.js.map