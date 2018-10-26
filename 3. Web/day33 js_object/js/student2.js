/*
 * 객체 추상화
 * */

//Object (packageX)
var kosta = {};
//(package 따로 관리 가능)
kosta.school = {};

//package > 'kosta.school'
kosta.school.Student = function(name, korean, math, english, science){
	this.name		= name;
	this.korean 	= korean;
	this.math 		= math;
	this.english 	= english;
	this.science 	= science;
}
