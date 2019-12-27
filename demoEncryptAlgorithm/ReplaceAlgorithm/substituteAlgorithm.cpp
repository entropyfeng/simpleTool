#include"substituteAlgorithm.h"
std::vector<std::tuple<int, int>> findSequence(std::vector<char> dir, std::string key)
{
	using TUP = std::tuple<char, int>;
	using TUP1 = std::tuple<int, int>;
	std::vector<TUP> ve;
	std::vector<TUP1> ve1;
	for (int i = 0; i < key.size(); i++) {
		ve.push_back(TUP(key[i], i));
	}
	//按照字典顺序进行排序
	std::sort(ve.begin(), ve.end(), [&](TUP a, TUP b) {
		auto pos_a = std::find(dir.begin(), dir.end(), std::get<0>(a));
		auto pos_b = std::find(dir.begin(), dir.end(), std::get<0>(b));
		return a < b;
	});

	for (int i = 0; i < ve.size(); i++)
	{
		int beforeMapping = std::get<1>(ve[i]);
		int afterMapping = i;
		ve1.push_back(TUP1(beforeMapping, afterMapping));
	}

	return ve1;
}

int convert(int pos, std::vector<char> dir, std::string key)
{
	std::map<int, int> mapping;
	for (auto temp : findSequence(dir, key)) {
		
		mapping.insert(std::make_pair(std::get<0>(temp), std::get<1>(temp)));
	}
	

	return mapping.at(pos);
}

int deConvert(int pos, std::vector<char> dir, std::string key)
{
	std::map<int, int> mapping;
	for (auto temp : findSequence(dir, key)) {
		mapping.insert(std::make_pair(std::get<1>(temp), std::get<0>(temp)));
	}
	return mapping.at(pos);
}
std::string encrypt(const std::vector<char> dir, std::string plain, std::string key)
{


	//n列
	int n= key.size();
	//m行
	int m = (int)(plain.size() / n);
	if (plain.size()%n != 0)m++;
	
	std::string result(m*n, '#');


	//填充plain至key长度的整数倍
	plain.append(m*n - plain.size(), '#');
	
	for (int i = 0; i < m; i++)
	{
		for (int j = 0; j < n; j++)
		{
			//std::cout << "current index:" << i * n + j << ",result:" << result.length() << ",plain:" << plain.length() << "\n";
			result[i*n + convert(j, dir, key)] = plain[i*n + j];
		}
	}
	
	return result;
}

std::string decrypt(std::vector<char> dir, std::string secret, std::string key)
{
	//n列
	int n = key.size();
	//m行
	int m = (int)(secret.size() / n);
	
	std::string result(m*n,'#');

	for (int i = 0; i < m; i++)
	{
		for (int j = 0; j < n; j++)
		{
			result[i*n + deConvert(j,dir,key)] = secret[i*n + j];
		}
	}

	return result.erase(result.find_first_of('#'));
}

