package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.Login.Auth;
import com.squareup.picasso.Picasso;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class agents extends AppCompatActivity {
 public int current = 0;
 String buddies  = "{\n" +
         "\"ba08f19b-496c-bc8d-45ff-c0a05759a0e6\":\"https://media.valorant-api.com/buddies/452194fb-4ebd-353a-2ff7-0e90cf5523f9/displayicon.png\",\n" +
         "\"633f5deb-41d2-b9d2-b099-0dbbfd7b3a69\":\"https://media.valorant-api.com/buddies/d6f5e6a4-4d42-b56d-03c3-92955d294f54/displayicon.png\",\n" +
         "\"dadb46d4-4760-141f-aa50-948f3054006e\":\"https://media.valorant-api.com/buddies/853da947-486d-feb3-ec2d-8ba25a6b545b/displayicon.png\",\n" +
         "\"58584a97-4839-3cb0-6f21-02809648df92\":\"https://media.valorant-api.com/buddies/d400dd1a-4a81-1b26-b8f1-a994e13739b2/displayicon.png\",\n" +
         "\"98a2fa9e-4e66-eec9-8b13-298498862f28\":\"https://media.valorant-api.com/buddies/8fbf09a5-4ad2-46ca-d44e-26b46587a1bf/displayicon.png\",\n" +
         "\"8bd42b70-4f2f-5c7d-33d3-0f83521c8eb5\":\"https://media.valorant-api.com/buddies/fedceff5-4a3e-0506-dbc0-8ba5b9cfd171/displayicon.png\",\n" +
         "\"cc4be40d-47e0-7f1e-2e30-9b90ce2607cd\":\"https://media.valorant-api.com/buddies/57bc159c-4bf9-1963-884a-a387e69013be/displayicon.png\",\n" +
         "\"c8b53af9-4e6d-5599-f516-43a22c1048ab\":\"https://media.valorant-api.com/buddies/c8259af3-4720-5312-0ba1-f7b65ee39834/displayicon.png\",\n" +
         "\"0af0c7ca-4fb7-0410-5cc6-a5834d340c53\":\"https://media.valorant-api.com/buddies/c5cdbaa1-4518-22ea-2e35-2aa3ad55ce9f/displayicon.png\",\n" +
         "\"b01739f3-4da9-0792-aa40-1094bc99e34d\":\"https://media.valorant-api.com/buddies/8e7b95e3-42d2-1b29-af2e-749c4ea24aa9/displayicon.png\",\n" +
         "\"8975b579-4df4-4b31-4ff0-5db942480054\":\"https://media.valorant-api.com/buddies/f1c7487e-4f9a-42d4-8704-0597d7146c26/displayicon.png\",\n" +
         "\"0af262b7-48a1-df12-d445-bd8747efc248\":\"https://media.valorant-api.com/buddies/919e0b68-4ac0-3ab4-0b1d-80b58103c844/displayicon.png\",\n" +
         "\"7d1864a2-4796-ac3c-a49f-7588ae17ed18\":\"https://media.valorant-api.com/buddies/99813567-4b6a-47c2-d36f-0ba5bc89adac/displayicon.png\",\n" +
         "\"96502717-41f7-5d24-89a1-66aff04bbbbb\":\"https://media.valorant-api.com/buddies/2fe275a6-41f2-bf54-7fb9-7593429ea675/displayicon.png\",\n" +
         "\"deef49c1-432d-10f7-a750-90b323b079d2\":\"https://media.valorant-api.com/buddies/2a1514dd-433d-c2e4-23a0-c1a75ccae00b/displayicon.png\",\n" +
         "\"0bd4e4b8-48bd-df5a-3af3-f49acf7913e1\":\"https://media.valorant-api.com/buddies/64245bfe-4740-fc95-95a4-81b4ec4d3412/displayicon.png\",\n" +
         "\"826b3b8b-4fdc-c686-e5ec-ad917e22dbfe\":\"https://media.valorant-api.com/buddies/15e4057c-4446-064d-98c7-249e479d8501/displayicon.png\",\n" +
         "\"e9579f35-46c7-2ba1-bec7-739c0e2778fa\":\"https://media.valorant-api.com/buddies/8374dfd2-4424-4805-e987-fd859b986d7a/displayicon.png\",\n" +
         "\"2030984a-4b8d-7cca-4eee-51aaa46b7712\":\"https://media.valorant-api.com/buddies/12b3da72-47ec-a773-5da4-a390e6321cb6/displayicon.png\",\n" +
         "\"2179e882-430e-be93-b909-bba9568e66af\":\"https://media.valorant-api.com/buddies/7ac1f7db-42a7-bf5a-5b13-95b70e618f5e/displayicon.png\",\n" +
         "\"0df7158a-46c6-eeb6-9561-e78165756b99\":\"https://media.valorant-api.com/buddies/e0462e6c-4188-06cc-e28d-ac9abcc16bc2/displayicon.png\",\n" +
         "\"31e2cc61-4d9e-2aa1-ff65-76a7a0a7bba1\":\"https://media.valorant-api.com/buddies/0f1ce8ce-4615-637d-6772-0fa15b848fe4/displayicon.png\",\n" +
         "\"eea4991c-4b01-9dd1-5d3c-1cbbdc8e1b0b\":\"https://media.valorant-api.com/buddies/fe997250-41ef-444a-a12b-7ca8ca87cb40/displayicon.png\",\n" +
         "\"967652c0-48c5-1b18-ae3e-2bb066f2623d\":\"https://media.valorant-api.com/buddies/271c1309-4185-d000-c8bf-56a170c17e1c/displayicon.png\",\n" +
         "\"e6eee5ec-4f6f-e764-1c4d-048ce271be27\":\"https://media.valorant-api.com/buddies/c5bee9f1-4548-03c3-f88d-d7bf38d1943e/displayicon.png\",\n" +
         "\"3da96301-4edd-0d74-492c-6e9d0766665a\":\"https://media.valorant-api.com/buddies/faca42f0-47d9-0fcb-116f-9e9b37060bd1/displayicon.png\",\n" +
         "\"28227664-476a-72db-adaa-159084fbd149\":\"https://media.valorant-api.com/buddies/d5b10115-43ea-42e7-8843-5bb5c1e73c6e/displayicon.png\",\n" +
         "\"f7005d97-4328-d473-dbb5-068a192f4f3a\":\"https://media.valorant-api.com/buddies/b0e6b3db-45fe-f622-52f2-51811650560e/displayicon.png\",\n" +
         "\"682a5c4e-4506-8d3a-82f9-6d87bd000dc7\":\"https://media.valorant-api.com/buddies/42afb96b-4d11-e465-55a4-0b87bdfbf244/displayicon.png\",\n" +
         "\"8fae3ccd-44e0-42e6-d60c-a39e1471cdc9\":\"https://media.valorant-api.com/buddies/910ea2d6-4578-c826-2b06-1594769766fb/displayicon.png\",\n" +
         "\"f3a3c59b-4bf3-461c-f956-d0bc7d95a742\":\"https://media.valorant-api.com/buddies/26d1b452-461d-8d6f-526b-658c692e0f9e/displayicon.png\",\n" +
         "\"b2128a8c-49bd-09b1-7b4c-f2b9d2f92508\":\"https://media.valorant-api.com/buddies/38a10a3b-495c-eeff-b8af-c3b2b5cdc3f9/displayicon.png\",\n" +
         "\"ed95e342-4116-f07c-88b9-368a5fadb224\":\"https://media.valorant-api.com/buddies/2de08c48-4aa1-0c32-138d-4b834c21aee2/displayicon.png\",\n" +
         "\"05b6bc52-4bc1-6568-1c1b-53830b2cf57e\":\"https://media.valorant-api.com/buddies/3c9bfa19-4688-39ff-5377-3cac9d396a32/displayicon.png\",\n" +
         "\"ef6ddfab-45f9-7938-7ff2-9588dec2f71d\":\"https://media.valorant-api.com/buddies/82bdb8b5-40bf-9b65-272e-4eb7dad1264e/displayicon.png\",\n" +
         "\"09f1c54f-4c4a-6338-424d-ef9378fe9885\":\"https://media.valorant-api.com/buddies/2d462f8e-47af-0678-dfa1-c3bc7c307ca7/displayicon.png\",\n" +
         "\"4fc451ca-439f-f3f3-e8ac-81bec328744e\":\"https://media.valorant-api.com/buddies/bcc2578f-46aa-c5a3-46bb-4ebfbbcf9b98/displayicon.png\",\n" +
         "\"ffa13bca-4bb7-5f3e-48a3-4e96c05a2df9\":\"https://media.valorant-api.com/buddies/b9926112-49d0-b049-b078-798851912eb7/displayicon.png\",\n" +
         "\"175d5ea6-43a6-1e39-e720-62805544f5d3\":\"https://media.valorant-api.com/buddies/0262276c-4ede-a858-cad0-1ca4e877052b/displayicon.png\",\n" +
         "\"6b26e5ca-4187-cb98-b7b2-9280343e32e3\":\"https://media.valorant-api.com/buddies/b45b70fb-49d4-9d7f-afde-57a1ea79d9b5/displayicon.png\",\n" +
         "\"461ee5bc-4325-67f1-9e3f-42952fb8f898\":\"https://media.valorant-api.com/buddies/108a36d5-44ef-9b4c-b783-b29a6a9ae340/displayicon.png\",\n" +
         "\"3b9e1d2c-4ba4-4fea-ceaa-dbb73a324a9a\":\"https://media.valorant-api.com/buddies/c839377c-4c9a-9562-28be-91805c2d94f9/displayicon.png\",\n" +
         "\"a7555579-4df9-6916-e15f-8381c11689a9\":\"https://media.valorant-api.com/buddies/41859a85-4344-aeee-483a-468bad39e5fd/displayicon.png\",\n" +
         "\"10d781c0-4500-19eb-5000-c6afac6f2c58\":\"https://media.valorant-api.com/buddies/e4689a4b-4df6-86e8-8d34-a19bdc01afe7/displayicon.png\",\n" +
         "\"abd3d1c7-42b6-46fc-35d2-ac9cf1f33607\":\"https://media.valorant-api.com/buddies/e058e4f5-40c4-a26a-e6c7-33a4baf2e21f/displayicon.png\",\n" +
         "\"d606df6a-4f8a-1273-083a-5b925a178e36\":\"https://media.valorant-api.com/buddies/ae240163-4893-5d7e-9029-31a3ca2a9c84/displayicon.png\",\n" +
         "\"ffa52256-404c-8c43-39e2-37aceda2bcb6\":\"https://media.valorant-api.com/buddies/8848ff77-43b1-5f73-8106-778f1ea8593e/displayicon.png\",\n" +
         "\"011602d8-4078-c9b3-8a3e-ac9642d0417c\":\"https://media.valorant-api.com/buddies/ebbced2d-4851-6b5d-ff3f-5baaea322dbf/displayicon.png\",\n" +
         "\"80bbcdff-4b2c-2370-0806-498c0f4e1d06\":\"https://media.valorant-api.com/buddies/8ac9948e-4c40-bcec-4f7d-e6bd5c8b349c/displayicon.png\",\n" +
         "\"bfa14167-42c6-a2e5-c406-1ea4289cc3d1\":\"https://media.valorant-api.com/buddies/32910c32-45e3-4959-9322-1a9fda1126ca/displayicon.png\",\n" +
         "\"eaf6e89a-46b6-83e3-2efb-eda4b0f3dd86\":\"https://media.valorant-api.com/buddies/e1f62dc9-4bd1-868e-3ca3-f7b51fd325fa/displayicon.png\",\n" +
         "\"f14d17fd-42a2-946f-21fb-1a815a6837bc\":\"https://media.valorant-api.com/buddies/12372dbb-4f09-4b26-6fe5-779488aaa9f9/displayicon.png\",\n" +
         "\"5b863cb9-4e41-ec89-69ee-49a223d05ff1\":\"https://media.valorant-api.com/buddies/1e909ee9-4af5-5d50-aa27-2bb596187986/displayicon.png\",\n" +
         "\"49ea4428-4511-ba9b-31db-64a9e552383f\":\"https://media.valorant-api.com/buddies/15031b6f-47f4-d8e5-6025-4fb692c841a1/displayicon.png\",\n" +
         "\"6f7b0b5b-470d-77b0-bd0d-7781ce5fdf07\":\"https://media.valorant-api.com/buddies/d8c85cae-44fd-8d97-a948-25acfe80b109/displayicon.png\",\n" +
         "\"e370d9f9-4deb-9ae4-a594-24947cb21b2d\":\"https://media.valorant-api.com/buddies/9f6bada3-4911-7863-ff39-6984140e0201/displayicon.png\",\n" +
         "\"03b6fbf7-4228-0712-3d91-da847f958b1b\":\"https://media.valorant-api.com/buddies/7b7d715a-48ff-ce97-4d7f-6cb75d763f7f/displayicon.png\",\n" +
         "\"ec3745c4-432a-8173-8150-cfbb4841c121\":\"https://media.valorant-api.com/buddies/f696f391-4b0e-804c-0069-02a0d67dd170/displayicon.png\",\n" +
         "\"55127784-49f1-afb7-a1ce-6699424088df\":\"https://media.valorant-api.com/buddies/bbd92afb-4a79-8b18-d723-78a169466703/displayicon.png\",\n" +
         "\"6fc24467-43fd-7c59-a531-94a1db4fdf75\":\"https://media.valorant-api.com/buddies/d12a80c0-44a0-0549-cc1f-eeb83f7ad248/displayicon.png\",\n" +
         "\"f4307224-4297-b7a7-caf0-1ba9dab5c855\":\"https://media.valorant-api.com/buddies/fbc2dfb9-4a95-aac5-85e0-69b405302ccf/displayicon.png\",\n" +
         "\"7d6fc129-4d33-11ae-3e1e-faba40f7b77f\":\"https://media.valorant-api.com/buddies/1f2bf8a7-45a9-9969-7a55-01833ce34fb1/displayicon.png\",\n" +
         "\"5dcdc42b-4398-0a62-1351-9b8cfdd2892c\":\"https://media.valorant-api.com/buddies/2d28fbc9-4518-3432-d90d-de8bba571a93/displayicon.png\",\n" +
         "\"7484a99e-4f26-0b7d-d07d-75ab4fa1da7b\":\"https://media.valorant-api.com/buddies/e711c5ad-4f71-ff78-7868-eaa7182265c0/displayicon.png\",\n" +
         "\"4b5417ed-49de-7076-e0fb-12a5b43957ce\":\"https://media.valorant-api.com/buddies/ac72bb9a-4368-8502-5dac-698d72021c81/displayicon.png\",\n" +
         "\"d59590fa-4389-125a-6c53-c7bd026acb01\":\"https://media.valorant-api.com/buddies/57639cf0-4b2f-73f6-5c37-fe816fa560df/displayicon.png\",\n" +
         "\"73466a36-43e4-232f-63be-c390f8c5e139\":\"https://media.valorant-api.com/buddies/164a994d-45bf-573a-5322-70b8b1d57887/displayicon.png\",\n" +
         "\"b0e1c557-4c45-bdc7-3e02-1facc7b19182\":\"https://media.valorant-api.com/buddies/bc76826c-4ac3-5c94-11af-1fb97ba3a835/displayicon.png\",\n" +
         "\"85569af3-4b78-8085-8325-8fa380a81845\":\"https://media.valorant-api.com/buddies/bd57203c-45d3-e5c0-7c61-6793f368de1a/displayicon.png\",\n" +
         "\"ec22d3c1-4df0-f0fe-a424-359627fb83ff\":\"https://media.valorant-api.com/buddies/b9feb6b6-493c-b76e-ce8e-6fb371ca2cf5/displayicon.png\",\n" +
         "\"e64b89a7-44ed-286e-08bf-0cb96fd2bfd2\":\"https://media.valorant-api.com/buddies/b5d5b1f3-40a2-07dc-f111-3aa87c596d7c/displayicon.png\",\n" +
         "\"aa3661f1-4eef-e8dd-047b-e5b9ed61403b\":\"https://media.valorant-api.com/buddies/15bc068c-4b1e-dfbc-cfbb-9283a8d0e0ee/displayicon.png\",\n" +
         "\"456a7d74-4cf8-5cf7-7801-f2a0265da233\":\"https://media.valorant-api.com/buddies/8bb77c22-4f90-ec3e-a2f7-16a2ddb064b2/displayicon.png\",\n" +
         "\"c1c9ca0a-477f-3388-7198-6cb90c36e6ae\":\"https://media.valorant-api.com/buddies/f2b96400-4663-c2cf-699d-029e8b52836f/displayicon.png\",\n" +
         "\"56f98991-43f8-dcc8-189e-08b7ae6c42ad\":\"https://media.valorant-api.com/buddies/839c6e7d-4821-157b-fd38-71b3debc874f/displayicon.png\",\n" +
         "\"d88fa813-4ade-1585-0865-90be2c984214\":\"https://media.valorant-api.com/buddies/42cb4b6a-45e3-8a83-2f52-0d90c7ca306d/displayicon.png\",\n" +
         "\"86cd790e-4992-4095-8522-f797f4c5a8f8\":\"https://media.valorant-api.com/buddies/be53b9d4-41de-3917-8f43-e58024a6e0de/displayicon.png\",\n" +
         "\"77dc86dc-4d9c-df35-3921-d18d32355824\":\"https://media.valorant-api.com/buddies/abfe3887-40f8-d3c1-08bb-ab89dae2a399/displayicon.png\",\n" +
         "\"d04a8b93-48a1-9eba-9d16-67b2cd46223e\":\"https://media.valorant-api.com/buddies/f74a52a2-4882-ee22-a26b-b0be558a6ced/displayicon.png\",\n" +
         "\"671e7df6-41fa-9801-3f37-14864d2fc7cc\":\"https://media.valorant-api.com/buddies/91697361-47ed-cc36-7c12-beb1d88c8631/displayicon.png\",\n" +
         "\"9e1ff7bf-4189-06fa-d6d2-ecaa23a22863\":\"https://media.valorant-api.com/buddies/820b4f0c-4d1f-bb6a-397a-7b9f4b48fff9/displayicon.png\",\n" +
         "\"044ac3d7-4035-2577-8b63-758584438bd0\":\"https://media.valorant-api.com/buddies/e2a83cb9-4168-4e3a-4023-2da045cd4cb3/displayicon.png\",\n" +
         "\"069a6c90-4ae6-d05e-e203-74bdc4aa7ece\":\"https://media.valorant-api.com/buddies/59631503-4448-3448-c650-4cbc9470266f/displayicon.png\",\n" +
         "\"5a490584-4f37-63a2-d96f-2fa55ed4f209\":\"https://media.valorant-api.com/buddies/a749e586-440a-4085-a020-cbb03612053d/displayicon.png\",\n" +
         "\"ff5ebb41-410f-3715-40a1-c4859ec912c2\":\"https://media.valorant-api.com/buddies/28b6dc7e-46a0-0baa-01fa-318e6c0d9f08/displayicon.png\",\n" +
         "\"d475f530-4a71-9165-953c-20b806c5d50e\":\"https://media.valorant-api.com/buddies/6fd8192b-4045-a7c2-057f-6180c39b2545/displayicon.png\",\n" +
         "\"35a7bbea-421e-40c1-9e11-4ca860420725\":\"https://media.valorant-api.com/buddies/f37c078d-4505-e27a-d3f4-77b15795bd32/displayicon.png\",\n" +
         "\"2484e328-45e9-a5c0-960f-ad8b0f620d0e\":\"https://media.valorant-api.com/buddies/90356707-45e1-5d0b-2b34-2d9aadf78b4e/displayicon.png\",\n" +
         "\"d1472303-4e9c-2c04-0ab6-95b7f787612a\":\"https://media.valorant-api.com/buddies/a1907073-46c5-6a62-17bb-a186d437a728/displayicon.png\",\n" +
         "\"9ea89ec3-4cb5-40f1-27af-f3983af8ab4d\":\"https://media.valorant-api.com/buddies/1a838fd4-43c3-dad7-ad30-37b9240c1ce1/displayicon.png\",\n" +
         "\"cc03fd3e-470a-7d06-47ce-f3a01eee15c2\":\"https://media.valorant-api.com/buddies/1ab94e60-428a-0037-53ba-c3b9da93a36b/displayicon.png\",\n" +
         "\"6fcab0f4-4160-1a8d-a297-1cb73e8514b7\":\"https://media.valorant-api.com/buddies/1c54de09-444d-61c3-11a8-47b94f22c05e/displayicon.png\",\n" +
         "\"9fc93fe7-49da-859d-9bd8-5c875971703d\":\"https://media.valorant-api.com/buddies/721dd4f3-4217-7e78-4a02-b99097f4d457/displayicon.png\",\n" +
         "\"a72a5cd8-4010-8e76-7f72-e1bdf663fa1c\":\"https://media.valorant-api.com/buddies/affee4fe-4b57-dddd-beb2-deaf0a641b25/displayicon.png\",\n" +
         "\"097e59dd-42ae-d86a-1caa-159d1b2ee838\":\"https://media.valorant-api.com/buddies/e4267845-4725-ff8e-6c71-ae933844565f/displayicon.png\",\n" +
         "\"f344bead-bb25-4226-a990-b5d9694d475d\":\"https://media.valorant-api.com/buddies/0eab96da-ed34-4bdb-978f-b02b2fb4ebcc/displayicon.png\",\n" +
         "\"547c3aa6-4e86-199c-b190-01b75287f30b\":\"https://media.valorant-api.com/buddies/a8e2dac4-4be6-75e7-bf76-1f9b5a6d5fb5/displayicon.png\",\n" +
         "\"7a5b1b4d-4f41-474f-e52b-60b3d2db7a25\":\"https://media.valorant-api.com/buddies/278e4d19-4026-02ac-263e-bca2b69df8fb/displayicon.png\",\n" +
         "\"f7626cd3-411c-a4e6-754b-eb8ee06c8377\":\"https://media.valorant-api.com/buddies/0ec28d81-498c-af58-722c-baa60a84151c/displayicon.png\",\n" +
         "\"7ea8a2f2-4727-b5c0-f193-94a921e22408\":\"https://media.valorant-api.com/buddies/134a2077-426b-c0ea-28bd-04b2cb0a1c72/displayicon.png\",\n" +
         "\"45db2427-4fec-75e1-da50-d286048dad53\":\"https://media.valorant-api.com/buddies/4e32934e-4ae1-2223-b9e4-90be4bd9677e/displayicon.png\",\n" +
         "\"47b3b0d7-4bb7-8654-4371-d2875c13c986\":\"https://media.valorant-api.com/buddies/547c6463-41b5-af1e-95ba-c690d734349c/displayicon.png\",\n" +
         "\"92d1dddf-4250-d0c7-d270-25b84990211d\":\"https://media.valorant-api.com/buddies/db524f8d-46eb-6a7d-cc74-e9a0ccafeeee/displayicon.png\",\n" +
         "\"e88f43b7-4b9a-1050-c1e7-d883a8f706f2\":\"https://media.valorant-api.com/buddies/5de7339f-40fc-df25-1cf4-d3a5255ba1f7/displayicon.png\",\n" +
         "\"5224eeaf-4387-0750-eb5c-0da41a9b243c\":\"https://media.valorant-api.com/buddies/2fbe33aa-4e7a-b601-5fa8-ce8fb6511113/displayicon.png\",\n" +
         "\"0b3f4807-4b50-2379-9690-398af0041dca\":\"https://media.valorant-api.com/buddies/1f99d997-4afb-ef4c-e754-caa0bf84fce1/displayicon.png\"\n" +
         "}";
 HashMap<String, String> AgentContractID = new HashMap<String, String>();
 HashMap<String , String > TypeToUrl = new HashMap<String , String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents);
        Intent i = getIntent();
        Auth auth = (Auth) i.getSerializableExtra("auth");
        auth.getAgents();
        ArrayList<String> data = auth.getAgentsSplash();
        ImageSlider slider = findViewById(R.id.slider);
        ArrayList<String> free_agents = new ArrayList<String>();
        free_agents.add("320b2a48-4d9b-a075-30f1-1f93a9b638fa");
        free_agents.add("eb93336a-449b-9c1b-0a54-a891f7921d69");
        free_agents.add("9f0d8ba9-4140-b941-57d3-a7ad57c6b417");
        free_agents.add("569fdd95-4d10-43ab-ca70-79becc718b46");
        free_agents.add("add6443a-41bd-e414-f6ad-e58d267f4e95");
        TypeToUrl.put("d5f120f8-ff8c-4aac-92ea-f2b5acbe9475" ,"https://media.valorant-api.com/sprays/");
        TypeToUrl.put("3f296c07-64c3-494c-923b-fe692a4fa1bd" ,"https://media.valorant-api.com/playercards/");
        TypeToUrl.put("de7caa6b-adf7-4588-bbd1-143831e786c6" ,"https://valorant-api.com/v1/playertitles/");
        TypeToUrl.put("01bb38e1-da47-4e6a-9b3d-945fe4655707" ,"https://media.valorant-api.com/agents/");
        TypeToUrl.put("dd3bf334-87f3-40bd-b043-682a57a8dc3a" ,"https://media.valorant-api.com/buddylevels/ID/displayicon.png");
        TypeToUrl.put("e7c63390-eda7-46e0-bb7a-a6abdacd2433" ,"https://media.valorant-api.com/weaponskinlevels/");
        AgentContractID.put("5f8d3a7f-467b-97f3-062c-13acf203c006" , "bfb8160e-eee0-46b1-a069-16f93adc7328");
        AgentContractID.put("f94c3b30-42be-e959-889c-5aa313dba261" , "60f9f1f0-2bb7-47f9-85b7-b873a5a1123b");
        AgentContractID.put("6f2a04ca-43e0-be17-7f36-b3908627744d" , "e7e7c5e1-4e76-22f8-f423-078b33758464");
        AgentContractID.put("117ed9e3-49f3-6512-3ccf-0cada7e3823b" , "2195e89f-20ad-4e37-b46c-cf46a6715dfd");
        AgentContractID.put("ded3520f-4264-bfed-162d-b080e2abccf9" , "3051fb18-9240-4bf3-a9f5-eb9ae954cd9d");
        AgentContractID.put("320b2a48-4d9b-a075-30f1-1f93a9b638fa" , "3051fb18-9240-4bf3-a9f5-eb9ae954cd9d");
        AgentContractID.put("1e58de9c-4950-5125-93e9-a0aee9f98746" , "9443cbd4-da4d-4395-8152-26a5b269f339");
        AgentContractID.put("707eab51-4836-f488-046a-cda6bf494859" , "f94fc320-a71f-47e3-b062-6798d14f17d6");
        AgentContractID.put("eb93336a-449b-9c1b-0a54-a891f7921d69" , "62b5521c-93f6-4178-aadd-043ed25ed21a");
        AgentContractID.put("41fb69c1-4189-7b37-f117-bcaf1e96f1bf" , "1d40b4b9-4d86-50b7-9f79-3d939e09c661");
        AgentContractID.put("9f0d8ba9-4140-b941-57d3-a7ad57c6b417" , "ace2bb52-de25-45b5-8e11-3dd2088f914d");
        AgentContractID.put("7f94d92c-4234-0a36-9646-3a87eb8b5c89" , "358b6e88-4cbe-0cfb-c313-c290eba0c8bc");
        AgentContractID.put("569fdd95-4d10-43ab-ca70-79becc718b46" , "e13d0f6f-5727-43bc-af9e-56c10cdb7176");
        AgentContractID.put("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc" , "4c9b0fcf-57cd-4e84-ae5a-ce89e396242f");
        AgentContractID.put("8e253930-4c05-31dd-1b6c-968525494517" , "eb35d061-4eed-4d22-81a3-1491ec892429");
        AgentContractID.put("add6443a-41bd-e414-f6ad-e58d267f4e95" , "c9d1c451-12fc-4601-a97c-8258765fb90d");
        auth.GetContracts();
        String contracts = auth.getAll_contracts();
        String mycontracts = auth.getMycontracts();
        List<SlideModel>  slidemod = new ArrayList<>();
        for (int j = 0 ; j<data.size();j++){
            try {
                JSONObject d = new JSONObject(data.get(j));
                slidemod.add(new SlideModel(d.getString("displayIcon")   , ScaleTypes.CENTER_INSIDE ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> myagents = auth.getMyagents();
        slider.setImageList(slidemod);
        slider.setItemChangeListener(new ItemChangeListener() {
            @Override
            public void onItemChanged(int i) {
                try {
                    current = i;
                    ((ImageButton) findViewById(R.id.info_agent)).performClick();
                    JSONObject d = new JSONObject(data.get(i));
                    ((TextView)findViewById(R.id.agent_name)).setText(d.getString("displayName"));
                    Picasso.get().load(d.getString("bustPortrait")).into((ImageView) findViewById(R.id.agent_portrait));

                    Picasso.get().load(d.getJSONObject("role").getString("displayIcon")).into((ImageView) findViewById(R.id.info_agent));
                    Picasso.get().load(d.getJSONArray("abilities").getJSONObject(0).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab1));
                    Picasso.get().load(d.getJSONArray("abilities").getJSONObject(1).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab2));
                    Picasso.get().load(d.getJSONArray("abilities").getJSONObject(2).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab3));
                    Picasso.get().load(d.getJSONArray("abilities").getJSONObject(3).getString("displayIcon")).into((ImageButton)findViewById(R.id.ulti));
                    ((TextView)findViewById(R.id.agent_role)).setText(d.getJSONObject("role").getString("displayName"));
                    if (myagents.contains(d.getString("uuid")) || free_agents.contains(d.getString("uuid"))){
                        ((TextView)findViewById(R.id.ownership)).setText("UNLOCKED");
                        ((TextView)findViewById(R.id.ownership)).setTextColor(Color.parseColor("#4CAF50"));
                    }
                    else{
                        ((TextView)findViewById(R.id.ownership)).setText("LOCKED");
                        ((TextView)findViewById(R.id.ownership)).setTextColor(Color.parseColor("#CC3232"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        ((ImageButton) findViewById(R.id.info_agent)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject d = new JSONObject(data.get(current));
                    ((TextView)findViewById(R.id.ab_title)).setText(d.getJSONObject("role").getString("displayName"));
                    ((TextView)findViewById(R.id.ab_desc)).setText(d.getJSONObject("role").getString("description"));
                    ((TextView)findViewById(R.id.info_desc)).setVisibility(View.VISIBLE);
                    ((TextView)findViewById(R.id.info_desc)).setText(d.getString("description"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ((ImageButton)findViewById(R.id.ab1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject d = new JSONObject(data.get(current));
                    ((TextView)findViewById(R.id.ab_title)).setText(d.getJSONArray("abilities").getJSONObject(0).getString("displayName"));
                    ((TextView)findViewById(R.id.ab_desc)).setText(d.getJSONArray("abilities").getJSONObject(0).getString("description"));
                    ((TextView)findViewById(R.id.info_desc)).setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ((ImageButton)findViewById(R.id.ab2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject d = new JSONObject(data.get(current));
                    ((TextView)findViewById(R.id.ab_title)).setText(d.getJSONArray("abilities").getJSONObject(1).getString("displayName"));
                    ((TextView)findViewById(R.id.ab_desc)).setText(d.getJSONArray("abilities").getJSONObject(1).getString("description"));
                    ((TextView)findViewById(R.id.info_desc)).setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ((ImageButton)findViewById(R.id.ab3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject d = new JSONObject(data.get(current));
                    ((TextView)findViewById(R.id.ab_title)).setText(d.getJSONArray("abilities").getJSONObject(2).getString("displayName"));
                    ((TextView)findViewById(R.id.ab_desc)).setText(d.getJSONArray("abilities").getJSONObject(2).getString("description"));
                    ((TextView)findViewById(R.id.info_desc)).setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ((ImageButton)findViewById(R.id.ulti)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject d = new JSONObject(data.get(current));
                    ((TextView)findViewById(R.id.ab_title)).setText(d.getJSONArray("abilities").getJSONObject(3).getString("displayName"));
                    ((TextView)findViewById(R.id.ab_desc)).setText(d.getJSONArray("abilities").getJSONObject(3).getString("description"));
                    ((TextView)findViewById(R.id.info_desc)).setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        JSONObject d = null;
        try {
            d = new JSONObject(data.get(current));
            ((TextView)findViewById(R.id.agent_name)).setText(d.getString("displayName"));
            ((TextView)findViewById(R.id.agent_role)).setText(d.getJSONObject("role").getString("displayName"));
            Picasso.get().load(d.getString("bustPortrait")).into((ImageView) findViewById(R.id.agent_portrait));

            Picasso.get().load(d.getJSONObject("role").getString("displayIcon")).into((ImageView) findViewById(R.id.info_agent));
            Picasso.get().load(d.getJSONArray("abilities").getJSONObject(0).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab1));
            Picasso.get().load(d.getJSONArray("abilities").getJSONObject(1).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab2));
            Picasso.get().load(d.getJSONArray("abilities").getJSONObject(2).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab3));
            Picasso.get().load(d.getJSONArray("abilities").getJSONObject(3).getString("displayIcon")).into((ImageButton)findViewById(R.id.ulti));

            ((ImageButton) findViewById(R.id.info_agent)).performClick();
            if (myagents.contains(d.getString("uuid")) || free_agents.contains(d.getString("uuid"))){
                ((TextView)findViewById(R.id.ownership)).setText("UNLOCKED");
                ((TextView)findViewById(R.id.ownership)).setTextColor(Color.parseColor("#4CAF50"));
            }
            else{
                ((TextView)findViewById(R.id.ownership)).setText("LOCKED");
                ((TextView)findViewById(R.id.ownership)).setTextColor(Color.parseColor("#CC3232"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ImageView card_img = (ImageView) findViewById(R.id.card_img);
        ImageView rank_img = (ImageView) findViewById(R.id.rank_img);
        Picasso.get().load("https://raw.githubusercontent.com/RumbleMike/ValorantStreamOverlay/main/Resources/TX_CompetitiveTier_Large_"+auth.getRank_id()+".png").into(rank_img);
        Picasso.get().load(auth.get_Card()).into(card_img);
        TextView name = (TextView) findViewById(R.id.name_id);
        name.setText(Html.fromHtml("<b>" +auth.getName() + "</b>" +  "#" + auth.getTag()) );
        ((TextView) findViewById(R.id.rank_name)).setText(auth.getRank());
        ProgressBar pbar =  ((ProgressBar) findViewById(R.id.rank_bar));
        pbar.setProgress(Integer.parseInt(auth.getLp()));
        ((TextView) findViewById(R.id.lp_txt)).setText(auth.getLp() + "/100");
        ((TextView) findViewById(R.id.valo_pt)).setText(auth.getVal_points());
        ((TextView) findViewById(R.id.radiant_pt_logo)).setText(auth.getRadiant_points());
        ((Button)findViewById(R.id.view_contract)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ConstraintLayout)findViewById(R.id.contract)).setVisibility(View.VISIBLE);
                ((ConstraintLayout)findViewById(R.id.all_stuff)).setAlpha((float) 0.5);
                ((ConstraintLayout)findViewById(R.id.all_constraint)).setBackgroundColor(Color.parseColor("#6C6767"));
                String current_Agent = null;
                try {
                    current_Agent = new JSONObject(data.get(current)).getString("uuid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String contract  = AgentContractID.get(current_Agent);
                long prog = 0;
                long current_level = 0;
                long xp = 0;

                try {
                    JSONArray data = new JSONObject(mycontracts).getJSONArray("Contracts");
                    for (int k = 0 ; k<data.length();k++){
                        if (data.getJSONObject(k).getString("ContractDefinitionID").equals(contract)){
                            current_level = data.getJSONObject(k).getLong("ProgressionLevelReached");
                             prog = data.getJSONObject(k).getLong("ProgressionTowardsNextLevel");
                             break;
                        }
                    }
                    if (current_level  == 10){
                        ((TextView)findViewById(R.id.xp)).setText("Completed");
                        ((TextView)findViewById(R.id.Tier_number)).setText("Tier 10/10");
                        ((ProgressBar)findViewById(R.id.progressBar2)).setMax(1);
                        ((ProgressBar)findViewById(R.id.progressBar2)).setProgress(1);
                    }
                    else{

                        JSONArray datas = new JSONObject(contracts).getJSONArray("ContractDefinitions");
                        for (int k = 0 ; k <datas.length();k++){
                            if (datas.getJSONObject(k).getString("ID").equals(contract)){
                                int progs = datas.getJSONObject(k).getJSONObject("ProgressionSchedule").getJSONArray("ProgressionDeltaPerLevel").getInt((int) current_level);
                                xp = progs - prog;
                                Log.d("XP" , String.valueOf(xp));
                                ((ProgressBar)findViewById(R.id.progressBar2)).setMax(progs);
                                ((ProgressBar)findViewById(R.id.progressBar2)).setProgress((int) prog);
                                JSONObject reward = datas.getJSONObject(k).getJSONArray("RewardSchedules").getJSONObject(0).getJSONArray("RewardsPerLevel").getJSONObject((int)current_level).getJSONArray("EntitlementRewards").getJSONObject(0);
                                String url = "";https://media.valorant-api.com/sprays/a64b8eda-4920-434a-5346-30bfb1b58e39/displayicon.png
                                //buddies
                                if (reward.getString("ItemTypeID").equals("dd3bf334-87f3-40bd-b043-682a57a8dc3a")){
                                    url = new JSONObject(buddies).getString(reward.getString("ItemID"));
                                }
                                //sprays
                                else if (reward.getString("ItemTypeID").equals("d5f120f8-ff8c-4aac-92ea-f2b5acbe9475")){
                                    url = "https://media.valorant-api.com/sprays/"+reward.getString("ItemID")+"/displayicon.png";
                                }
                                //cards
                                else if(reward.getString("ItemTypeID").equals("3f296c07-64c3-494c-923b-fe692a4fa1bd")){
                                    url = "https://media.valorant-api.com/playercards/"+reward.getString("ItemID")+"/wideart.png";
                                }
                                //agent
                                else if (reward.getString("ItemTypeID").equals("01bb38e1-da47-4e6a-9b3d-945fe4655707")){
                                    url = "https://media.valorant-api.com/agents/"+ reward.getString("ItemID")+ "/displayicon.png";
                                }
                                //skin
                                else if (reward.getString("ItemTypeID").equals("e7c63390-eda7-46e0-bb7a-a6abdacd2433")){
                                    url = "https://media.valorant-api.com/weaponskinlevels/"+ reward.getString("ItemID")+ "/displayicon.png";
                                }
                                else{
                                    url = "https://media.discordapp.net/attachments/691417976951537697/751496476429123644/20200904_183815.jpg?width=225&height=300";
                                }
                                Picasso.get().load(url).into((ImageView)findViewById(R.id.imageView));

                            }
                        }
                        ((TextView)findViewById(R.id.xp)).setText(xp + " XP to go");
                        ((TextView)findViewById(R.id.Tier_number)).setText("Tier " +  (int) (current_level + 1) + "/10");

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        ((ImageButton)findViewById(R.id.close_c)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ConstraintLayout)findViewById(R.id.contract)).setVisibility(View.GONE);
                ((ConstraintLayout)findViewById(R.id.all_stuff)).setAlpha((float) 1);
                ((ConstraintLayout)findViewById(R.id.all_constraint)).setBackgroundColor(Color.parseColor("#BD5555"));

            }
        });






    }
}