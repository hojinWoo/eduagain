/*
 * 객체 추상화
 * */

/*사용자 정의 객체(생성자)*/
//function prototype이 상위.
function Student(name, korean, math, english, science){
	//this를 꼭 써주어야 key와 value가 instance로 된다. (instance변수 X)
	//공개이기 때문에 property
	this.name		= name;
	this.korean 	= korean;
	this.math 		= math;
	this.english 	= english;
	this.science 	= science;
}
//class 변수 개념이랑 비슷 (static 변수X)
Student.schoolName = 'CCCC College';


//prototype에 method 저장
//map 구조
Student.prototype.getSum = function(){
	return this.korean + this.math + this.english + this.science;
}

Student.prototype.getAverage = function(){
	return this.getSum() / 4;
}

Student.prototype.toString = function(){
	return this.name +"\t" + this.korean +"\t" + this.math +"\t" + this.english +"\t" + this.science + "\t" + this.getSum() + "\t" + this.getAverage();
}