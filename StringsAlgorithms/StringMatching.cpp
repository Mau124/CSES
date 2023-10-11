#include<bits/stdc++.h>
using namespace std;

// RabinKarp algorithm
// Another solution is to kmp algo
const int m = 1e6+1;
const int p = 31;

long long powers[m];

int main() {
    string text, pattern;
    cin >> text >> pattern;

    // Calculat hash
    // hash(s) -> (s[0]*p^0) % m + (s[1]*p^1) % m + ... + (s[n]*p^n) % m
    // Pre-compute the powers
    powers[0] = 1;
    for(int i=1; i<21; ++i) {
        powers[i] = (powers[i-1]*p)%m;
    }

    // Calculate hash of pattern
    int hashP = 0;
    for(int i=0; i<pattern.size(); ++i) {
        hashP = (hashP + ((pattern[i] - 'a' + 1)*powers[i]) % m) % m;
    }

    // cout << "Value of hash: " << hashP << endl;

    // Search for occurences
    long long cur = 0;
    long long ans = 0;
    for(int i=0; i<text.size(); ++i) {
        
        if(i >= pattern.size()) {
            // cout << "Value of text:: " << text[i-pattern.size()] << endl;
            // cout << "Value of text:: " << (text[i-pattern.size()] - 'a' + 1) << endl;
            // cout << "value of power:: " << powers[i-pattern.size()] << endl;
            // cout << "Value of substraendo:: " << ((text[i-pattern.size()] - 'a' + 1)*powers[i-pattern.size()]) << endl;
            cur = cur + m - ((text[i-pattern.size()] - 'a' + 1)*powers[i-pattern.size()])%m;
            // cout << "Value of cur after:: " << cur << endl;
        }

        cur = (cur + ((text[i] - 'a' + 1)*powers[i])%m) % m;
        
        if(i >= pattern.size()-1) {
            // cout << "Value of cur before:: " << cur << endl;
            // cout << "Value of hashP*powers:: " << (hashP*powers[i-(pattern.size()-1)])%m << endl;
            if(cur == (hashP*powers[i-(pattern.size()-1)]) % m) {
                ans++;
            }
        }
    }

    cout << ans << endl;

    return 0;
}