#include<random>
#include<chrono>

#include"substituteAlgorithm.h"
void traversal(std::vector<char> dir) {
	
	for (auto temp : dir) {
		std::cout << temp << " ";
	}
	std::cout << std::endl;
}
void generateDir(std::vector<char> &dir) {
	
	//初始化字典(a-z A-Z 0-9)	
	for (char i = 'a'; i <= 'z'; i++)
	{
		dir.push_back(i);
	}
	for (char i = 'A'; i <= 'Z'; i++)
	{
		dir.push_back(i);
	}
	for (char i = '0'; i <= '9'; i++)
	{
		dir.push_back(i);
	}
	dir.push_back(' ');
	dir.push_back('.');

	//随机化字典
	unsigned seed = std::chrono::system_clock::now().time_since_epoch().count();
	//std::shuffle(dir.begin(), dir.end(), std::default_random_engine(seed));

}


int main() {

	std::vector<char> dir;
	generateDir(dir);
	std::string plain = "excuse me hello world haha ";
	//int key = 4;
	//std::string screct=encrypt(dir, plain, key);
	//std::string result = decrypt(dir, screct, key);
	//std::cout << screct << std::endl;
	//std::cout << result << std::endl;

	std::string key = "cipher";
	std::string screct = encrypt(dir, plain, key);
	std::string result = decrypt(dir, screct, key);

	std::cout << screct << std::endl;
	std::cout << result << std::endl;	


	getchar();
	return 0;
}