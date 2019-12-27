#include"replaceAlgorithm.h"
std::string encrypt(const std::vector<char> dir, std::string plain, int key) {
	std::string result("");
	for (auto temp : plain) {
		auto pos = std::find(dir.begin(), dir.end(), temp);
		if (pos == dir.end()) {
			std::cout << "Exist undefined char !" << std::endl;
			exit(0);
		}

		char a = dir.at((std::distance(dir.begin(), pos) + key) % dir.size());
		result.push_back(a);
	}
	return result;
}
std::string decrypt(std::vector<char> dir, std::string secret, int key) {

	std::string result("");
	for (auto temp : secret) {
		auto pos = std::find(dir.begin(), dir.end(), temp);
		if (pos == dir.end()) {
			std::cout << "Exist undefined char !" << std::endl;
			exit(0);
		}
		char a = dir.at((std::distance(dir.begin(), pos) + dir.size() - key) % dir.size());
		result.push_back(a);
	}
	return result;

}