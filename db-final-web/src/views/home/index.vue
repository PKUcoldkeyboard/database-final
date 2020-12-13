<template>
	<div>
		<a-row :gutter="[16,16]">
				<a-col :span="5":offset="1">
					排序：
					<a-select default-value="默认">
						<a-select-option value="默认">默认</a-select-option>
						<a-select-option value="时间">时间</a-select-option>
						<a-select-option value="人气">人气</a-select-option>
					</a-select>
				</a-col>
				<a-col :span="6">
				</a-col>
				<a-col :span="6">
				</a-col>
				<a-col :span="6">
				</a-col>
		</a-row>
		<a-row :gutter="[16,16]">
			<a-col :span="5"
				   :offset="1"
					style="height:1000px;">
				<h1 class="category-h1">图书类别</h1>
				<a-list item-layout="horizontal">
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
				</a-list>
				<h1 class="category-h1">年度畅销书</h1>
				<a-list item-layout="horizontal">
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
					<a-list-item>
						<a-list-item-meta >
							<router-link to="/" slot="title"><a-icon type="right" style="font-size: 10px;color:rgb(70,70,70);"/>&nbsp;科技历史</router-link>
						</a-list-item-meta>
					</a-list-item>
				</a-list>
				<h1 class="category-h1">价格区间</h1>

			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card hoverable
						style="margin: auto;width: 80%;height: 100%;">
					<img slot="cover"
						 alt="example"
						 src="http://139.224.22.9:9090/db-final/20201007/%E8%93%9D%E8%89%B2%E6%80%A5%E6%B5%81.jpeg"
						 />
					<a-card-meta title="book_name" description="Description"/>
				</a-card>
			</a-col>
		</a-row>
		<a-row :gutter="[16,16]">
			<a-col :span="8">
			</a-col>
			<a-col :span="12">
				<a-pagination show-quick-jumper :default-current="1" :total="300"/>
			</a-col>
			<a-col :span="4">
			</a-col>
		</a-row>
	</div>
</template>

<script>
export default{
	data(){
		return{

		}
	},
}
</script>

<style scoped>
	.category-h1{
		font-size: 18px;
		font-weight: bold;
	}
</style>