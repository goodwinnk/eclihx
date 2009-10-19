package test;

class Index {
	static function main() {
    	var params = php.Web.getParams();
    	var name = params.exists('name') ? params.get('name') : 'world';
		php.Lib.print('Hello ' + name + '!');
  	}
};