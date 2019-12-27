#pragma once
#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<map>
std::string encrypt(const std::vector<char> dir, std::string plain, std::string key);
std::string decrypt(std::vector<char> dir, std::string secret, std::string key);
