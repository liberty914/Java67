/* 실습 목표: 자바스크립트 파일 압축하기 */
function bit(t){var e;if("<"==t.charAt(0)){var n=t.indexOf(">"),r=t.substring(1,n);e=[document.createElement(r)]}else e=document.querySelectorAll(t);return e.val=function(t){if(arguments.length>0){for(var e=0;e<this.length;e++)this[e].value=t;return this}return this[0].value},e.attr=function(t,e){if(2==arguments.length){for(var n=0;n<this.length;n++)this[n].setAttribute(t,e);return this}return 1==arguments.length?this[0].getAttribute(t):void 0},e.css=function(t,e){if(2==arguments.length){for(var n=0;n<this.length;n++)this[n].style[t]=e;return this}return 1==arguments.length?this[0].style[t]:void 0},e.text=function(t){if(1==arguments.length){for(var e=0;e<this.length;e++)this[e].textContent=t;return this}return 0==arguments.length?this[0].textContent:void 0},e.html=function(t){if(1==arguments.length){for(var e=0;e<this.length;e++)this[e].innerHTML=t;return this}return 0==arguments.length?this[0].innerHTML:void 0},e.click=function(t){if(t){for(var e=0;e<this.length;e++)this[e].addEventListener("click",t);return this}for(var n=new MouseEvent("click",{bubbles:!0,cancelable:!0,view:window}),e=0;e<this.length;e++)this[e].dispatchEvent(n)},e.append=function(t){for(var e=0;e<this.length;e++)for(var n=0;n<t.length;n++)this[e].appendChild(t[n]);return this},e.remove=function(){for(var t=0;t<this.length;t++)this[t].parentElement.removeChild(this[t]);return this},e.appendTo=function(t){for(var n=0;n<this.length;n++)for(var r=0;r<t.length;r++)t[r].appendChild(e[n]);return this},e}bit.ajax=function(t,e){var n=new XMLHttpRequest;if(n.onreadystatechange=function(){if(4==n.readyState){var t=JSON.parse(n.responseText);"success"==t.status?e.success&&e.success(t):e.error&&e.error(t.data)}},n.open(e.method,t,!0),"POST"==e.method){n.setRequestHeader("Content-type","application/x-www-form-urlencoded");var r="";if(e.data)for(var i in e.data)r.length>0&&(r+="&"),r+=i+"="+encodeURIComponent(e.data[i]);n.send(r)}else n.send(null)};var $=bit;