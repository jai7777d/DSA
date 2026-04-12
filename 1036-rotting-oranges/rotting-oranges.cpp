class Solution {
public:
    int orangesRotting(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = grid[0].size();

        queue<pair<pair<int,int>,int>> q;

        // ✅ FIX 1: vis ko initialize kiya
        vector<vector<int>> vis(n, vector<int>(m, 0));

        // Step 1: push all rotten oranges into queue
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==2){
                    q.push({{i,j},0});
                    vis[i][j]=2; // mark visited
                }
            }
        }

        int tm=0;

        // directions: up, right, down, left
        int drow[]={-1,0,1,0};
        int dcol[]={0,1,0,-1};

        // Step 2: BFS
        while(!q.empty()){
            int r = q.front().first.first;
            int c = q.front().first.second;
            int t = q.front().second;
            q.pop();

            tm = max(tm,t);

            for(int i=0; i<4;i++){
                int nrow = r + drow[i];
                int ncol = c + dcol[i];

                // ✅ FIX 2: only fresh oranges (grid == 1)
                // ✅ FIX 3: correct assignment (= instead of ==)
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m 
                   && !vis[nrow][ncol] && grid[nrow][ncol]==1){

                    q.push({{nrow,ncol},t+1});
                    vis[nrow][ncol] = 2; // mark as rotten
                }
            }
        }

        // Step 3: check if any fresh orange left
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1 && vis[i][j]!=2){
                    return -1; // fresh orange not reached
                }
            }
        }

        return tm;
    }
};